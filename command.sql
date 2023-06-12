CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  email TEXT UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  predictions_id INT[],
  mini_league_code VARCHAR(10),
  total_points FLOAT,
  rewards_id INT[]
  FOREIGN KEY (mini_league_code) REFERENCES mini_leagues (code) ON DELETE CASCADE,
);

CREATE TABLE teams (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  crest TEXT NOT NULL,
  odds FLOAT NOT NULL
);

CREATE TYPE match_status AS ENUM ('Upcoming', 'Ongoing', 'Finished');

CREATE TABLE fixtures (
  id SERIAL PRIMARY KEY,
  home_id INT,
  home_score INT NOT NULL,
  home_odds FLOAT,
  away_id INT,
  away_score INT NOT NULL,
  away_odds FLOAT,
  date DATE NOT NULL,
  status match_status NOT NULL,
  gameweek INT NOT NULL,
  FOREIGN KEY (home_id) REFERENCES teams (id) ON DELETE CASCADE,
  FOREIGN KEY (away_id) REFERENCES teams (id) ON DELETE CASCADE
);


CREATE TABLE predictions (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  fixture_id INT NOT NULL,
  pred_home INT NOT NULL,
  pred_away INT NOT NULL,
  points FLOAT,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (fixture_id) REFERENCES fixtures (id) ON DELETE CASCADE
);

CREATE TABLE mini_leagues (
  code VARCHAR(10) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  image TEXT,
  users_table_id INT,
  FOREIGN KEY (users_table_id) REFERENCES mini_league_users (id) ON DELETE CASCADE
);

CREATE TABLE mini_league_users(
  id SERIAL PRIMARY KEY,
  mini_league_code VARCHAR(10) NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (mini_league_code) REFERENCES mini_leagues (code) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE rewards (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  type VARCHAR(30) NOT NULL,
  image TEXT,
  cost BIGINT NOT NULL
);

INSERT INTO fixtures (home_id, home_score, home_odds, away_id, away_score, away_odds, date, status, gameweek)
VALUES
  (9, 0, 1.0, 12, 0, 2.1, '2022-08-06', 'Upcoming', 1),
  (8, 0, 0.6, 6, 0, 1.1, '2022-08-06', 'Upcoming', 1),
  (14, 0, 3.8, 4, 0, 1.5, '2022-08-07', 'Upcoming', 1),
  (1, 0, 4.2, 11, 0, 0.6, '2022-08-13', 'Ongoing', 2),
  (3, 0, 1.1, 14, 0, 3.8, '2022-08-13', 'Upcoming', 2),
  (6, 0, 1.1, 18, 0, 1.8, '2022-08-14', 'Upcoming', 2);

INSERT INTO rewards (name, type, image, cost)
VALUES
    ('Google Play $5 Giftcard', 'Giftcard', 'https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj', 999),
    ('Google Play $10 Giftcard', 'Giftcard', 'https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj', 1999),
    ('Google Play $15 Giftcard', 'Giftcard', 'https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj', 2999),
    ('Google Play $20 Giftcard', 'Giftcard', 'https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj', 3999),
    ('Google Play $100 Giftcard', 'Giftcard', 'https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj', 79999),
    ('Steam CD Key', 'Games', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Steam_icon_logo.svg/512px-Steam_icon_logo.svg.png', 11999),
    ('Gopay IDR 100K', 'Cash', 'https://gopay.co.id/icon.png', 1299);


INSERT INTO teams (name, crest, odds)
VALUES
    ('Arsenal', 'https://crests.football-data.org/57.png', 4.2),
    ('Aston Villa', 'https://crests.football-data.org/58.png', 1.3),
    ('Brentford', 'https://crests.football-data.org/402.png', 1.1),
    ('Brighton', 'https://crests.football-data.org/397.svg', 1.5),
    ('Bournemouth', 'https://crests.football-data.org/1044.png', 0.7),
    ('Chelsea', 'https://crests.football-data.org/61.png', 1.1),
    ('Crystal Palace', 'https://crests.football-data.org/354.png', 1.0),
    ('Everton', 'https://crests.football-data.org/62.png', 0.6),
    ('Fulham', 'https://crests.football-data.org/63.svg', 1.0),
    ('Leeds United', 'https://crests.football-data.org/341.png', 0.5),
    ('Leicester City', 'https://crests.football-data.org/338.png', 0.6),
    ('Liverpool', 'https://crests.football-data.org/64.png', 2.1),
    ('Manchester City', 'https://crests.football-data.org/65.png', 4.5),
    ('Manchester United', 'https://crests.football-data.org/66.png', 3.8),
    ('Newcastle Utd', 'https://crests.football-data.org/67.png', 3.5),
    ('Nottingham Forest', 'https://crests.football-data.org/351.png', 0.7),
    ('Southampton', 'https://crests.football-data.org/340.png', 0.5),
    ('Tottenham', 'https://crests.football-data.org/73.svg', 1.8),
    ('West Ham Utd', 'https://crests.football-data.org/563.png', 0.9),
    ('Wolves', 'https://crests.football-data.org/76.svg', 0.8);
