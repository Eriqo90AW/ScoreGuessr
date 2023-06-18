const express = require('express');
const router = express.Router();

const sgAuthControllers = require('../controllers/sgAuthControllers');

router.get('/getUser', sgAuthControllers.authenticateToken, sgAuthControllers.getUser);
router.post('/refresh', sgAuthControllers.refreshTokens);

router.post('/login', sgAuthControllers.login);
router.post('/logout', sgAuthControllers.logout);

module.exports = router;