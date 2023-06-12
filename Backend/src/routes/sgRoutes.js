const express = require('express');
const router = express.Router();

const sgControllers = require('../controllers/sgControllers');

router.get('/', sgControllers.getAllUsers);
router.get('/teams', sgControllers.getAllTeams);
router.get('/fixtures', sgControllers.getAllFixtures);
router.get('/predictions', sgControllers.getAllPredictions);
router.get('/mini-leagues', sgControllers.getAllMiniLeagues);
router.get('/mini-leagues-users', sgControllers.getAllMiniLeagueUsers);
router.get('/rewards', sgControllers.getAllRewards);

router.post('/login', sgControllers.login);
router.post('/register', sgControllers.register);
router.delete('/delete/:id', sgControllers.deleteUser);
router.put('/updatePredictions/:id', sgControllers.updateUserPredictions);
router.put('/updatePoints/:id', sgControllers.updateUserTotalPoints);
router.put('/updateRewards/:id', sgControllers.updateUserRewards);
router.put('/updateMiniLeague/:id', sgControllers.updateUserMiniLeague);

router.post('/teams/create', sgControllers.createTeam);
router.delete('/teams/delete/:id', sgControllers.deleteTeam);
router.put('/teams/update/:id', sgControllers.updateTeam);

router.post('/fixtures/create', sgControllers.createFixture);
router.delete('/fixtures/delete/:id', sgControllers.deleteFixture);
router.put('/fixtures/update/:id', sgControllers.updateFixture);
router.put('/fixtures/updateStatus/:id', sgControllers.updateFixtureStatus);

router.post('/predictions/create', sgControllers.createPrediction);
router.delete('/predictions/delete/:id', sgControllers.deletePrediction);
router.put('/predictions/update/:id', sgControllers.updatePrediction);
router.put('/predictions/updatePoints/:id', sgControllers.updatePredictionPoints);

router.post('/mini-leagues/create', sgControllers.createMiniLeague);
router.delete('/mini-leagues/delete', sgControllers.deleteMiniLeague);
router.put('/mini-leagues/update', sgControllers.updateMiniLeague);

router.post('/mini-leagues/addUser', sgControllers.addMiniLeagueUser);
router.delete('/mini-leagues/removeUser', sgControllers.removeMiniLeagueUser);

router.post('/rewards/create', sgControllers.createReward);

router.get('/fixtures/upcoming', sgControllers.getUpcomingGames);
router.get('/mini-leagues/:code', sgControllers.getMiniLeagueUsers);
router.get('/mini-leagues/info/:code', sgControllers.getMiniLeagueInfo);
router.get('/mini-leagues/all/info', sgControllers.getAllMiniLeaguesInfo);
router.get('/rewards/:id', sgControllers.getAllUserRewards);

module.exports = router;
