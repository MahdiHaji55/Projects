const app = require("./app");
const connectDB = require("./config/db"); //Ensure this path matches your folder structure

require("dotenv").config(); // Load environment variables

connectDB(); //Call the function to connect MongoDB

const PORT = process.env.PORT || 3001;

const server = app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
