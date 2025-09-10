#!/bin/bash

# Color definitions
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${GREEN}🚀 Setting up PostgreSQL database...${NC}"

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo -e "${RED}❌ Docker is not running, please start Docker first${NC}"
    exit 1
fi

# Check if PostgreSQL container is already running
if docker ps | grep -q postgres; then
    echo -e "${YELLOW}⚠️  PostgreSQL container is already running${NC}"
else
    echo -e "${GREEN}🐘 Starting PostgreSQL container...${NC}"
    docker run --name postgres-nestjs \
        -e POSTGRES_PASSWORD=postgres \
        -e POSTGRES_USER=postgres \
        -e POSTGRES_DB=nestjs_auth \
        -p 5432:5432 \
        -d postgres:15
    
    # Wait for container to start
    echo -e "${YELLOW}⏳ Waiting for PostgreSQL to start...${NC}"
    sleep 5
fi

# Check database connection
echo -e "${GREEN}🔍 Checking database connection...${NC}"
if docker exec postgres-nestjs pg_isready -U postgres > /dev/null 2>&1; then
    echo -e "${GREEN}✅ PostgreSQL connection successful!${NC}"
    
    # Create database (if not exists)
    echo -e "${GREEN}📊 Creating database nestjs_auth (if not exists)...${NC}"
    docker exec postgres-nestjs psql -U postgres -c "CREATE DATABASE nestjs_auth;" 2>/dev/null || echo -e "${YELLOW}⚠️  Database may already exist${NC}"
    
    echo -e "${GREEN}🎉 Database setup complete!${NC}"
    echo -e "${GREEN}📝 Database connection info:${NC}"
    echo -e "  Host: localhost"
    echo -e "  Port: 5432"
    echo -e "  Database: nestjs_auth"
    echo -e "  Username: postgres"
    echo -e "  Password: postgres"
else
    echo -e "${RED}❌ Unable to connect to PostgreSQL${NC}"
    exit 1
fi
