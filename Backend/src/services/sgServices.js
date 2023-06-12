const { db } = require("../config/connectToDb");
const queries = require("../queries/sgQueries");
const bcrypt = require("bcrypt");

const length = 6;
const characters = '1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ';

async function getAllUsers(){
    const query = 'SELECT * FROM users';
    const result = await db.query(query);
    if (result.rows.length > 0) {
        return result.rows;
    }else{
        return {
            message: "No users found"
        }
    }
}

async function getAllTeams(){
    const query = 'SELECT * FROM teams';
    const result = await db.query(query);
    if (result.rows.length > 0) {
        return result.rows;
    }else{
        return {
            message: "No teams found"
        }
    }
}

async function getAllFixtures(){
    const query = 'SELECT * FROM fixtures';
    const result = await db.query(query);
    if (result.rows.length > 0) {
        return result.rows;
    }else{
        return {
            message: "No fixtures found"
        }
    }
}

async function getAllPredictions(){
    const query = 'SELECT * FROM predictions';
    const result = await db.query(query);
    if (result.rows.length > 0) {
        return result.rows;
    }else{
        return {
            message: "No predictions found"
        }
    }
}

async function getAllMiniLeagues(){
    const query = 'SELECT * FROM mini_leagues';
    const result = await db.query(query);
    if (result.rows.length > 0) {
        return result.rows;
    }else{
        return {
            message: "No mini leagues found"
        }
    }
}

async function getAllMiniLeagueUsers(){
    const query = 'SELECT * FROM mini_league_users';
    const result = await db.query(query);
    if (result.rows.length > 0) {
        return result.rows;
    }else{
        return {
            message: "No mini league users found"
        }
    }
}

async function getAllRewards(){
    const query = 'SELECT * FROM rewards';
    const result = await db.query(query);
    if (result.rows.length > 0) {
        return result.rows;
    }else{
        return {
            message: "No rewards found"
        }
    }
}

//USERS TABLE
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
            return user;
        }else{
            return {
                message: 'Password is not correct'
            }
        }
    }
}

async function register (user){
    const {username, password, email} = user;
    const pass = await bcrypt.hash(password, 10);
    const query = `INSERT INTO users (username, password, email) VALUES ($1, $2, $3)`;
    const values = [username, pass, email];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Register successful'
        }
    }else{
        return{
            message: 'Failed to register'
        } 
    }
}

async function deleteUser (id){
    const query = `DELETE FROM users WHERE id = $1`;
    const values = [id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'User deleted'
        }
    }else{
        return{
            message: 'Failed to delete user'
        }
    }
}

async function updateUserPredictions (id, user){
    const {predictions_id} = user;
    const query = `UPDATE users SET predictions_id = $1 WHERE id = $2`;
    const values = [predictions_id, id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'User predictions updated'
        }
    }else{
        return{
            message: 'Failed to update user predictions'
        }
    }
}

async function updateUserTotalPoints (id, user){
    const {total_points} = user;
    const query = `UPDATE users SET total_points = $1 WHERE id = $2`;
    const values = [total_points, id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'User total points updated'
        }
    }else{
        return{
            message: 'Failed to update user total points'
        }
    }
}

async function updateUserRewards (id, user){
    const {rewards_id} = user;
    const query = `UPDATE users SET rewards_id = array_append(rewards_id, $1) WHERE id = $2`;
    const values = [rewards_id, id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'User rewards added'
        }
    }else{
        return{
            message: 'Failed to add user rewards'
        }
    }
}

async function updateUserMiniLeague (id, user){
    const {mini_league_code} = user;
    const query = `UPDATE users SET mini_league_code = $1 WHERE id = $2`;
    const values = [mini_league_code, id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'User mini league updated'
        }
    }else{
        return{
            message: 'Failed to update user mini league'
        }
    }
}

//TEAMS TABLE
async function createTeam(team){
    const {name, crest, odds} = team;
    const query = `INSERT INTO teams (name, crest, odds) VALUES ($1, $2, $3)`;
    const values = [name, crest, odds];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Team created'
        }
    }else{
        return{
            message: 'Failed to create team'
        } 
    }
}

async function deleteTeam(id){
    const query = `DELETE FROM teams WHERE id = $1`;
    const values = [id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Team deleted'
        }
    }else{
        return{
            message: 'Failed to delete team'
        }
    }
}

async function updateTeam(id, team){
    const {name, crest, odds} = team;
    const query = `UPDATE teams SET name = $1, crest = $2, odds = $3 WHERE id = $4`;
    const values = [name, crest, odds, id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Team updated'
        }
    }else{
        return{
            message: 'Failed to update team'
        }
    }
}

//FIXTURES TABLE
async function createFixture(fixture){
    const {home_id, home_score, home_odds, away_id, away_score, away_odds, date, status, gameweek} = fixture;
    const query = `INSERT INTO fixtures (home_id, home_score, home_odds, away_id, away_score, away_odds, date, status, gameweek) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)`;
    const values = [home_id, home_score, home_odds, away_id, away_score, away_odds, date, status, gameweek];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Fixture created'
        }
    }else{
        return{
            message: 'Failed to create fixture'
        }
    }
}

async function deleteFixture(id){
    const query = `DELETE FROM fixtures WHERE id = $1`;
    const values = [id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Fixture deleted'
        }
    }else{
        return{
            message: 'Failed to delete fixture'
        }
    }
}

async function updateFixture(id, fixture){
    const {home_score, away_score, status} = fixture;
    const query = `UPDATE fixtures SET home_score = $1, away_score = $2, status = $3 WHERE id = $4`;
    const values = [home_score, away_score, status, id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Fixture updated'
        }
    }else{
        return{
            message: 'Failed to update fixture'
        }
    }
}

async function updateFixtureStatus(id, fixture){
    const {status} = fixture;
    const query = `UPDATE fixtures SET status = $1 WHERE id = $2`;
    const values = [status, id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Fixture status updated'
        }
    }else{
        return{
            message: 'Failed to update fixture status'
        }
    }
}

//PREDICTIONS TABLE
async function createPrediction(prediction){
    const {user_id, fixture_id, pred_home, pred_away} = prediction;
    const query = `INSERT INTO predictions (user_id, fixture_id, pred_home, pred_away) VALUES ($1, $2, $3, $4)`;
    const values = [user_id, fixture_id, pred_home, pred_away];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Prediction created'
        }
    }else{
        return{
            message: 'Failed to create prediction'
        }
    }
}

async function deletePrediction(id){
    const query = `DELETE FROM predictions WHERE id = $1`;
    const values = [id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Prediction deleted'
        }
    }else{
        return{
            message: 'Failed to delete prediction'
        }
    }
}

async function updatePrediction(id, prediction){
    const {pred_home, pred_away} = prediction;
    const query = `UPDATE predictions SET pred_home = $1, pred_away = $2 WHERE id = $3`;
    const values = [pred_home, pred_away, id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Prediction updated'
        }
    }else{
        return{
            message: 'Failed to update prediction'
        }
    }
}

async function updatePredictionPoints(id, prediction){
    const {points} = prediction;
    const query = `UPDATE predictions SET points = $1 WHERE id = $2`;
    const values = [points, id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Prediction points updated'
        }
    }else{
        return{
            message: 'Failed to update prediction points'
        }
    }
}

//MINI LEAGUES TABLE
async function createMiniLeague(miniLeague){
    const {name, image} = miniLeague;

    let code = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        code += characters.charAt(randomIndex);
    }

    const query = `INSERT INTO mini_leagues (code, name, image) VALUES ($1, $2, $3)`;
    const values = [code, name, image];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Mini league created'
        }
    }else{
        return{
            message: 'Failed to create mini league'
        }
    }
}

async function deleteMiniLeague(miniLeague){
    const {code} = miniLeague;
    const query = `DELETE FROM mini_leagues WHERE code = $1`;
    const values = [code];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Mini league deleted'
        }
    }else{
        return{
            message: 'Failed to delete mini league'
        }
    }
}

async function updateMiniLeague(miniLeague){
    const {code, image} = miniLeague;
    const query = `UPDATE mini_leagues SET image = $1 WHERE code = $2`;
    const values = [image, code];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Mini league image updated'
        }
    }else{
        return{
            message: 'Failed to update mini league image'
        }
    }
}

//MINI LEAGUE USERS TABLE
async function addMiniLeagueUser(miniLeagueUsers){
    const {mini_league_code, user_id} = miniLeagueUsers;
    const query = `INSERT INTO mini_league_users (mini_league_code, user_id) VALUES ($1, $2)`;
    const values = [mini_league_code, user_id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Mini league user added'
        }
    }else{
        return{
            message: 'Failed to add mini league user'
        }
    }
}

async function removeMiniLeagueUser(miniLeagueUsers){
    const {code, user_id} = miniLeagueUsers;
    const query = `DELETE FROM mini_league_users WHERE code = $1 AND user_id = $2`;
    const values = [code, user_id];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Mini league user removed'
        }
    }else{
        return{
            message: 'Failed to remove mini league user'
        }
    }
}

//REWARDS TABLE
async function createReward(reward){
    const {name, type, image, cost} = reward;
    const query = `INSERT INTO rewards (name, type, image, cost) VALUES ($1, $2, $3, $4)`;
    const values = [name, type, image, cost];
    const result = await db.query(query, values);
    if(result.rowCount === 1){
        return {
            message: 'Reward added'
        }
    }else{
        return{
            message: 'Failed to add reward'
        }
    }
}

//COMPLEX QUERIES
async function getUpcomingGames(){
    const query = queries.getUpcomingGames;
    const result = await db.query(query);
    if(result.rows.length > 0){
        return result.rows;
    }else{
        return {
            message: 'No upcoming games'
        }
    }
}

async function getMiniLeagueUsers(code){
    const query = queries.getMiniLeagueUsers;
    const values = [code];
    const result = await db.query(query, values);
    if(result.rows.length > 0){
        return result.rows;
    }else{
        return {
            message: 'No users in mini league'
        }
    }
}

async function getMiniLeagueInfo(code){
    const query = queries.getMiniLeagueInfo;
    const values = [code];
    const result = await db.query(query, values);
    if(result.rows.length > 0){
        return result.rows;
    }else{
        return {
            message: 'There is no information about this mini league'
        }
    }
}

async function getAllMiniLeaguesInfo(){
    const query = queries.getAllMiniLeaguesInfo;
    const result = await db.query(query);
    if(result.rows.length > 0){
        return result.rows;
    }else{
        return {
            message: 'No mini leagues found'
        }
    }
}

async function getAllUserRewards(id){
    const query = queries.getAllUserRewards;
    const values = [id];
    const result = await db.query(query, values);
    if(result.rows.length > 0){
        return result.rows;
    }else{
        return {
            message: 'No rewards found'
        }
    }
}

module.exports = { getAllUsers, 
                    getAllTeams,
                    getAllFixtures,
                    getAllPredictions,
                    getAllMiniLeagues,
                    getAllMiniLeagueUsers,
                    getAllRewards,
                    login, 
                    register, 
                    deleteUser,
                    updateUserPredictions,
                    updateUserRewards,
                    updateUserTotalPoints,
                    updateUserMiniLeague,
                    createTeam,
                    deleteTeam, 
                    updateTeam,
                    createFixture,
                    deleteFixture,
                    updateFixture,
                    updateFixtureStatus,
                    createPrediction,
                    deletePrediction,
                    updatePrediction,
                    updatePredictionPoints,
                    createMiniLeague,
                    deleteMiniLeague,
                    updateMiniLeague,
                    addMiniLeagueUser,
                    removeMiniLeagueUser,
                    createReward,
                    getUpcomingGames,
                    getMiniLeagueUsers,
                    getMiniLeagueInfo,
                    getAllMiniLeaguesInfo,
                    getAllUserRewards }