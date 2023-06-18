const { db } = require("../config/connectToDb");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");

function generateAccessToken(user) {
    return jwt.sign(user, process.env.ACCESS_TOKEN_SECRET, { expiresIn: "1d" });
}

async function refreshTokens(body){
    const {token} = body;
    const query = 'SELECT * FROM refresh_tokens WHERE token = $1';
    const values = [token];
    const result = await db.query(query, values);
    if (result.rows.length > 0) {
        let accessToken = "";
        let error = false
        jwt.verify(token, process.env.REFRESH_TOKEN_SECRET, (err, user) => {
            if (err) {
                return error = true;
            };
            accessToken = generateAccessToken({ username: user });
            return error = false;
        })
        if(error){
            return {
                message: "Invalid token"
            }
        }else{
            return {
                accessToken: accessToken
            }
        }
    }else{
        return {
            message: "No token found"
        }
    }
}

async function getUser(user){
    const {username} = user;
    const query = 'SELECT * FROM users WHERE username = $1';
    const values = [username];
    const result = await db.query(query, values);
    if (result.rows.length > 0) {
        return result.rows[0];
    }else{
        return {
            message: "No users found"
        }
    }
}

async function login (user){
    const {username, password} = user;
    const query = `SELECT * FROM users WHERE username = $1`;
    const values = [username];
    const result = await db.query(query, values);
    if(result.rows.length === 0){
        return {
            message: 'User not found'
        }
    }else{
        const user = result.rows[0];
        const pass = await bcrypt.compare(password, user.password);
        if(pass){
            const accessToken = generateAccessToken({username: user.username});
            const refreshToken = jwt.sign(user.username, process.env.REFRESH_TOKEN_SECRET);
            const query = `INSERT INTO refresh_tokens (token) VALUES ($1)`;
            const values = [refreshToken];
            await db.query(query, values);
            return{
                accessToken: accessToken,
                refreshToken: refreshToken,
            }
        }else{
            return {
                message: 'Password is not correct'
            }
        }
    }
}

async function logout (body){
    const {token} = body;
    const query = `DELETE FROM refresh_tokens WHERE token = $1`;
    const values = [token];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Logout successful'
        }
    }else{
        return{
            message: 'Failed to logout'
        }
    }
}

module.exports = {login, logout, refreshTokens, getUser};
