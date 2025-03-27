const redis = require("redis");

const client = redis.createClient({
  socket: {
    host: process.env.REDIS_HOST || "redis",
    port: process.env.REDIS_PORT || 6379,
    reconnectStrategy: (retries) => Math.min(retries * 100, 5000) //Better exponential backoff
  },
});

client.on("connect", () => console.log("Redis Connected"));
client.on("error", (err) => console.error("Redis Error:", err.message));

(async () => {
  try {
    await client.connect();
    console.log("Redis Connection Established");
  } catch (error) {
    console.error("Redis Connection Failed:", error.message);
  }
})();

module.exports = client;
