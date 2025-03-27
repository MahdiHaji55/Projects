FROM node:20-alpine

# Set working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json before running npm install
COPY package*.json ./

# Install dependencies
RUN npm install

# Create the src directory inside the container (if not already created)
RUN mkdir -p src

# Copy the entire src folder
COPY src/ src/

# Copy other necessary files (if applicable)
# COPY test_data/ test_data/

# Expose the correct port (should match `PORT` in .env & docker-compose)
EXPOSE 3001

# Start the server
CMD ["npm", "start"]