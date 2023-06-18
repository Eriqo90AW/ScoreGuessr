const express = require("express");
const bodyParser = require("body-parser");
const dotenv = require("dotenv");
dotenv.config();
const cors = require('cors');
const { connectToDB } = require("./src/config/connectToDb");
const sgRoutes = require("./src/routes/sgRoutes");
const sgAuthRoutes = require("./src/routes/sgAuthRoutes");

const app = express();
const PORT = process.env.PORT || 5000;

connectToDB();

app.use(cors());
app.use(express.json());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.use("/", sgRoutes);
app.use("/", sgAuthRoutes);

app.listen(PORT, () => {
  console.log("Server Running on port " + PORT);
});
