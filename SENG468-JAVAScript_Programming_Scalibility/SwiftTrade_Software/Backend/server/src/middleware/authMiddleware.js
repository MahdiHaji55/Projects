const jwt = require("jsonwebtoken");

module.exports = (req, res, next) => {
    let token = req.header("token"); //Check if token is in header

    //If token is not in the header, check inside req.body.data.token
    if (!token && req.body && req.body.data && req.body.data.token) {
        token = req.body.data.token;
    }

    if (!token) {
        return res.status(401).json({ success: false, data: { error: "Access denied. No token provided." } });
    }

    try {
        const decoded = jwt.verify(token, "your_secret"); //Decode JWT directly
        //console.log("Decoded Token:", decoded); // Debugging: Check if ID is present

        if (!decoded.id) {
            return res.status(400).json({ success: false, data: { error: "Invalid token payload" } });
        }

        req.user = { id: decoded.id, user_name: decoded.user_name }; //Attach user info to req
        next();

    } catch (error) {
        return res.status(401).json({ success: false, data: { error: "Invalid token" } });
    }
};
