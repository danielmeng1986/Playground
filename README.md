
# Project Idea

1. Users can log in to the application with a username and password.
2. The application uses JWT tokens as the authentication method for communication.
3. users with admin rights can create more users.
4. The main function of the application is to discover and tag Spielplatz in Germany.
5. Users can enter their zip code, PLZ, or city name to get information about neighboring Spielplatzes.
6. Each Spielplatz has its own address, opening hours, a brief description, several pictures and labels. For example, whether there are slides, rope toys, see-saws, sandy areas, whether soccer is allowed or not.
7. users can save their favorite Spielplatz and rate and comment on Spielplatz.

8. The list of Spielplatz can be sorted by rating, distance from their current location.

---

# Development Environment & Docker Setup

## Prerequisites

1. **Docker Desktop**: Install Docker Desktop for your OS (macOS/Linux/Windows). [Download here](https://www.docker.com/products/docker-desktop/)
2. **Node.js**: Not required for running in Docker, but useful for local development and troubleshooting.
3. **psql/PostgreSQL Client**: For connecting to the database from your host machine (optional, for debugging).

## How to Start the Project with Docker

1. **Build Docker Images** (if not already built):
   ```sh
   # From the project root
   docker build -t backend-nestjs:latest ./src/apps/backend-nestjs
   docker build -t frontend-angular:latest ./src/apps/frontend-angular
   ```

2. **Start All Services**
   ```sh
   docker compose up
   # Or run in background:
   docker compose up -d
   ```

3. **Access the Services**
   - Frontend: [http://localhost:4200/](http://localhost:4200/)
   - Backend API: [http://localhost:3000/](http://localhost:3000/)
   - PostgreSQL: Host `localhost`, Port `5432`, User `postgres`, Password `postgres`, DB `playground`

4. **Stop All Services**
   ```sh
   docker compose down
   ```

## Common Issues & Tips

- Make sure Docker Desktop is running before executing any docker commands.
- If you change dependencies or Dockerfile, rebuild the images before `docker compose up`.
- If you want to connect to the database from your host, use a tool like `psql`, DBeaver, or DataGrip with the credentials above.
- Do not use a browser to access `localhost:5432` (PostgreSQL is not an HTTP service).
- If you encounter port conflicts, ensure no other services are using 3000, 4200, or 5432 on your host.

## Example: Test Database Connection from Host

```sh
psql -h localhost -U postgres -p 5432 -d playground
# Password: postgres
```

---
