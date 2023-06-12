const getUpcomingGames = `
SELECT *
FROM fixtures
WHERE gameweek = (
    SELECT DISTINCT gameweek
    FROM fixtures
    WHERE status = 'Upcoming'
    LIMIT 1
);
`;

const getMiniLeagueUsers = `
SELECT u.username, u.predictions_id[1] AS M1, u.predictions_id[2] AS M2, u.predictions_id[3] AS M3, u.total_points AS PTS
FROM users u
JOIN mini_league_users mu ON u.id = mu.user_id
WHERE mu.mini_league_code = $1
ORDER BY PTS DESC;
`;

const getMiniLeagueInfo = `
SELECT ml.image AS image_url, ml.name AS mini_league_name, ml.code AS mini_league_code, COUNT(mu.id) AS total_users
FROM mini_leagues ml
JOIN mini_league_users mu ON ml.code = mu.mini_league_code
WHERE ml.code = $1
GROUP BY ml.image, ml.name, ml.code;
`;

const getAllMiniLeaguesInfo = `
SELECT ml.image AS image_url, ml.name AS mini_league_name, ml.code AS mini_league_code, COUNT(mu.id) AS total_users
FROM mini_leagues ml
JOIN mini_league_users mu ON ml.code = mu.mini_league_code
GROUP BY ml.image, ml.name, ml.code;
`;

const getAllUserRewards = `
SELECT r.id, r.name, r.type, r.image, LPAD(UPPER(SUBSTRING(md5(random()::text), 1, 13)), 13, '0') AS code
FROM rewards r
JOIN users u ON u.rewards_id @> ARRAY[r.id]
WHERE u.id = $1;
`;

module.exports = { getUpcomingGames, 
                    getMiniLeagueUsers,
                    getMiniLeagueInfo,
                    getAllMiniLeaguesInfo,
                    getAllUserRewards };
