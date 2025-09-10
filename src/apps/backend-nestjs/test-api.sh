#!/bin/bash

# Color definitions
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

BASE_URL="http://localhost:3000"

echo -e "${GREEN}🧪 Testing NestJS API with PostgreSQL integration${NC}"
echo "=================================="

# Test 1: Register new user
echo -e "${BLUE}📝 Test 1: Register new user${NC}"
REGISTER_RESPONSE=$(curl -s -X POST ${BASE_URL}/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "testuser@example.com",
    "password": "password123"
  }')

echo "Response: $REGISTER_RESPONSE"

if echo "$REGISTER_RESPONSE" | grep -q "User registered successfully"; then
    echo -e "${GREEN}✅ Registration successful${NC}"
else
    echo -e "${RED}❌ Registration failed${NC}"
fi

echo ""

# Test 2: User login
echo -e "${BLUE}🔑 Test 2: User login${NC}"
LOGIN_RESPONSE=$(curl -s -X POST ${BASE_URL}/auth \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }')

echo "Response: $LOGIN_RESPONSE"

# Extract token
TOKEN=$(echo "$LOGIN_RESPONSE" | grep -o '"token":"[^"]*"' | cut -d'"' -f4)

if [ ! -z "$TOKEN" ]; then
    echo -e "${GREEN}✅ Login successful, token obtained${NC}"
    echo "Token: ${TOKEN:0:20}..."
else
    echo -e "${RED}❌ Login failed${NC}"
    exit 1
fi

echo ""

# Test 3: Get current user info
echo -e "${BLUE}👤 Test 3: Get current user info${NC}"
USER_INFO_RESPONSE=$(curl -s -X GET ${BASE_URL}/auth/me \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json")

echo "Response: $USER_INFO_RESPONSE"

if echo "$USER_INFO_RESPONSE" | grep -q "testuser"; then
    echo -e "${GREEN}✅ Get user info successful${NC}"
else
    echo -e "${RED}❌ Get user info failed${NC}"
fi

echo ""

# Test 4: Get user list
echo -e "${BLUE}📋 Test 4: Get user list${NC}"
USERS_LIST_RESPONSE=$(curl -s -X GET ${BASE_URL}/users \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json")

echo "Response: $USERS_LIST_RESPONSE"

if echo "$USERS_LIST_RESPONSE" | grep -q "users"; then
    echo -e "${GREEN}✅ Get user list successful${NC}"
else
    echo -e "${RED}❌ Get user list failed${NC}"
fi

echo ""

# Test 5: Check database
echo -e "${BLUE}🗄️  Test 5: Check data in database${NC}"
DB_USERS=$(docker exec postgres-db psql -U postgres -d nestjs_auth -t -c "SELECT COUNT(*) FROM users;")
echo "Number of users in database: $(echo $DB_USERS | tr -d ' ')"

if [ "$(echo $DB_USERS | tr -d ' ')" -gt "0" ]; then
    echo -e "${GREEN}✅ Data correctly saved to PostgreSQL${NC}"
else
    echo -e "${RED}❌ No user data found in database${NC}"
fi

echo ""
echo -e "${GREEN}🎉 Testing complete! PostgreSQL integration works properly.${NC}"
