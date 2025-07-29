FROM node:20
WORKDIR /app
COPY package*.json ./
RUN npm install && npm install -g @openapitools/openapi-generator-cli
COPY . .
EXPOSE 3000
CMD ["npm", "run", "start"]
