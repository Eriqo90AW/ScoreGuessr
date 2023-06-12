const sgServices = require("../services/sgServices");

async function getAllUsers(req, res) {
  try {
      const result = await sgServices.getAllUsers();
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getAllTeams(req, res) {
  try {
      const result = await sgServices.getAllTeams();
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getAllFixtures(req, res) {
  try {
      const result = await sgServices.getAllFixtures();
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getAllPredictions(req, res) {
  try {
      const result = await sgServices.getAllPredictions();
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getAllMiniLeagues(req, res) {
  try {
      const result = await sgServices.getAllMiniLeagues();
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getAllMiniLeagueUsers(req, res) {
  try {
      const result = await sgServices.getAllMiniLeagueUsers(req.params.code);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getAllRewards(req, res) {
  try {
      const result = await sgServices.getAllRewards();
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

async function register(req, res) {
  try {
      const result = await sgServices.register(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function deleteUser(req, res) {
  try {
      const result = await sgServices.deleteUser(req.params.id);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function updateUserPredictions(req, res) {
  try {
      const result = await sgServices.updateUserPredictions(req.params.id, req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function updateUserRewards(req, res) {
  try {
      const result = await sgServices.updateUserRewards(req.params.id, req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function updateUserMiniLeague(req, res) {
  try {
      const result = await sgServices.updateUserMiniLeague(req.params.id, req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function updateUserTotalPoints(req, res) {
  try {
      const result = await sgServices.updateUserTotalPoints(req.params.id, req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function createTeam(req, res) {
  try {
      const result = await sgServices.createTeam(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function deleteTeam(req, res) {
  try {
      const result = await sgServices.deleteTeam(req.params.id);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function updateTeam(req, res) {
  try {
      const result = await sgServices.updateTeam(req.params.id, req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function createFixture(req, res) {
  try {
      const result = await sgServices.createFixture(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function deleteFixture(req, res) {
  try {
      const result = await sgServices.deleteFixture(req.params.id);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function updateFixture(req, res) {
  try {
      const result = await sgServices.updateFixture(req.params.id, req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function updateFixtureStatus(req, res) {
  try {
      const result = await sgServices.updateFixtureStatus(req.params.id, req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  } 
}

async function createPrediction(req, res) {
  try {
      const result = await sgServices.createPrediction(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function deletePrediction(req, res) {
  try {
      const result = await sgServices.deletePrediction(req.params.id);
      res.status(200).json(result);
  } catch (err) { 
      res.status(400).json({ message: err.message });
  }
}

async function updatePrediction(req, res) {
  try {
      const result = await sgServices.updatePrediction(req.params.id, req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function updatePredictionPoints(req, res) {
  try {
      const result = await sgServices.updatePredictionPoints(req.params.id, req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function createMiniLeague(req, res) {
  try {
      const result = await sgServices.createMiniLeague(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function deleteMiniLeague(req, res) {
  try {
      const result = await sgServices.deleteMiniLeague(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function updateMiniLeague(req, res) {
  try {
      const result = await sgServices.updateMiniLeague(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function addMiniLeagueUser(req, res) {
  try {
      const result = await sgServices.addMiniLeagueUser(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function removeMiniLeagueUser(req, res) {
  try {
      const result = await sgServices.removeMiniLeagueUser(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function createReward(req, res) {
  try {
      const result = await sgServices.createReward(req.body);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getUpcomingGames(req, res) {
  try {
      const result = await sgServices.getUpcomingGames();
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getMiniLeagueUsers(req, res) {
  try {
      const result = await sgServices.getMiniLeagueUsers(req.params.code);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getMiniLeagueInfo(req, res) {
  try {
      const result = await sgServices.getMiniLeagueInfo(req.params.code);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  }
}

async function getAllMiniLeaguesInfo(req, res) {
  try {
      const result = await sgServices.getAllMiniLeaguesInfo();
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
  } 
}

async function getAllUserRewards(req, res) {
  try {
      const result = await sgServices.getAllUserRewards(req.params.id);
      res.status(200).json(result);
  } catch (err) {
      res.status(400).json({ message: err.message });
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
