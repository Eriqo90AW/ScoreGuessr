const sgServices = require("../services/sgAuthServices");
const jwt = require("jsonwebtoken");

function authenticateToken(req, res, next) {
    const authHeader = req.headers["authorization"];
    const token = authHeader && authHeader.split(" ")[1];

    if (token == null) return res.sendStatus(401);

    jwt.verify(token, process.env.ACCESS_TOKEN_SECRET, (err, user) => {
        if (err) return res.sendStatus(403);
        req.user = user;
        next();
    });
}
  
async function refreshTokens(req, res) {
    try {
      const result = await sgServices.refreshTokens(req.body);
      res.status(200).json(result);
    } catch (err) {
      res.status(400).json({ message: err.message });
    }
}
  
async function getUser(req, res) {
    try {
        const result = await sgServices.getUser(req.user);
        res.status(200).json(result);
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
}

async function login(req, res) {
    try {
        const result = await sgServices.login(req.body);
        res.status(200).json(result);
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
}
  
async function logout(req, res) {
    try {
        const result = await sgServices.logout(req.body);
        res.status(200).json(result);
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
}

module.exports = { authenticateToken, refreshTokens, getUser, login, logout };
