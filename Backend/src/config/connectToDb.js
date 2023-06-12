const { Client } = require('pg');

const pgConnectionParams = {
  user: process.env.DB_USER,
  host: process.env.DB_HOST,
  database: process.env.DB_DATABASE,
  password: process.env.DB_PASS,
  port: process.env.DB_PORT,
  sslmode: process.env.DB_SSLMODE,
  ssl: process.env.DB_SSL
};

const db = new Client(pgConnectionParams);

const connectToDB = async () => {
  try {
    await db.connect();
    console.log("Database connection established");
  } catch (err) {
    console.error("Database connection error: " + err.message);
  }
};

module.exports = { connectToDB, db };