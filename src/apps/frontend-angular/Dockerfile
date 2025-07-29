FROM --platform=linux/amd64 node:20
WORKDIR /app
COPY package*.json ./
RUN npm install && npm install -g @angular/cli@20.0.4 @openapitools/openapi-generator-cli
RUN npm install @rollup/rollup-linux-x64-gnu
COPY . .
EXPOSE 4200
CMD ["npm", "run", "start"]
