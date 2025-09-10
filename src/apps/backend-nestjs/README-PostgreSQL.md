# PostgreSQL Integration Guide

This project has integrated PostgreSQL database, replacing the previous in-memory mock data.

## 🚀 Quick Start

### 1. Setup Database

Run the following command to automatically setup PostgreSQL container:

```bash
npm run db:setup
```

This will:
- Start a PostgreSQL Docker container
- Create a database named `nestjs_auth`
- Configure user and password

### 2. Start Application

```bash
npm run start:dev
```

When the application starts, TypeORM will automatically:
- Connect to PostgreSQL database
- Create necessary table structure (users table)
- Synchronize entity definitions

## 📊 Database Configuration

### Connection Information

- **Host**: localhost
- **Port**: 5432
- **Database**: nestjs_auth
- **Username**: postgres
- **Password**: postgres

### Environment Variables

The project uses `.env` file to configure database connection:

```env
DB_HOST=localhost
DB_PORT=5432
DB_USERNAME=postgres
DB_PASSWORD=postgres
DB_NAME=nestjs_auth
```

## 🏗️ Database Structure

### Users Table (users)

| Field | Type | Description |
|-------|------|-------------|
| UserID | integer | Primary key, auto-increment |
| FirstName | varchar(100) | First name |
| LastName | varchar(100) | Last name |
| Alias | varchar(50) | User alias (unique) |
| Email | varchar(255) | Email (unique) |
| password | varchar(255) | Encrypted password |
| createdAt | timestamp | Creation time |
| updatedAt | timestamp | Update time |

## 🔧 Major Changes

### 1. Entity Definition

`src/users/entities/user.entity.ts` - Defined TypeORM user entity

### 2. Service Layer Updates

`src/users/users.service.ts` - Uses TypeORM Repository instead of in-memory array

### 3. Authentication Service Updates

`src/auth/auth.service.ts` - Uses database for user authentication and registration

### 4. Database Configuration

`src/config/database.config.ts` - TypeORM configuration

## 🛠️ Development Commands

```bash
# Setup database
npm run db:setup

# Reset database (delete and recreate container)
npm run db:reset

# Start development server
npm run start:dev

# View database logs
docker logs postgres-nestjs

# Connect to database
docker exec -it postgres-nestjs psql -U postgres -d nestjs_auth
```

## 🔍 Test API

### Register New User

```bash
curl -X POST http://localhost:3000/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "password123"
  }'
```

### Login

```bash
curl -X POST http://localhost:3000/auth \
  -H "Content-Type: application/json" \
  -d '{
    "username": "test",
    "password": "password123"
  }'
```

### Get User List

```bash
curl -X GET http://localhost:3000/users \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 📝 Important Notes

1. **Production Environment**: Make sure to change default passwords and JWT secret
2. **Data Persistence**: Current configuration stores data in Docker container, deleting container will lose data
3. **Migration**: Production environment should disable `synchronize` and use migration files
4. **Backup**: Regularly backup production database

## 🐛 Troubleshooting

### Database Connection Failed

1. Ensure Docker is running
2. Check PostgreSQL container status: `docker ps`
3. View container logs: `docker logs postgres-nestjs`

### Port Conflict

If port 5432 is occupied, you can modify the port mapping in `setup-db.sh`.

### Permission Issues

Ensure `setup-db.sh` has execute permission: `chmod +x setup-db.sh`
