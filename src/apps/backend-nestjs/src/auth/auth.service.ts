import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { AuthDto, TokenDto } from './dto/auth.dto';
import { UserDto } from './dto/user.dto';
import { RegisterDto, RegisterResponseDto } from './dto/register.dto';
import {
  AuthExceptions,
  AuthExceptionFactory,
} from './exceptions/auth.exceptions';
import { UsersService } from '../users/users.service';
import { User } from '../users/entities/user.entity';
import * as bcrypt from 'bcrypt';

interface JwtPayload {
  username: string;
  sub: number;
  iat?: number;
  exp?: number;
}

// Type guard function
function isValidJwtPayload(obj: unknown): obj is JwtPayload {
  return (
    obj !== null &&
    typeof obj === 'object' &&
    'username' in obj &&
    'sub' in obj &&
    // eslint-disable-next-line @typescript-eslint/no-unsafe-member-access
    typeof (obj as any).username === 'string' &&
    // eslint-disable-next-line @typescript-eslint/no-unsafe-member-access
    typeof (obj as any).sub === 'number'
  );
}

@Injectable()
export class AuthService {
  constructor(
    private jwtService: JwtService,
    private usersService: UsersService,
  ) {}

  async register(registerDto: RegisterDto): Promise<RegisterResponseDto> {
    // Check if email already exists
    const existingUser = await this.usersService.findByEmail(registerDto.email);
    if (existingUser) {
      throw AuthExceptions.EMAIL_EXISTS(registerDto.email);
    }

    // ✅ 400 validation exception (if needed)
    if (!registerDto.email || !registerDto.password) {
      throw AuthExceptions.MISSING_CREDENTIALS();
    }

    // Generate user alias (extracted from email)
    const alias = registerDto.email.split('@')[0];

    // Encrypt password
    const saltRounds = 10;
    const hashedPassword = await bcrypt.hash(registerDto.password, saltRounds);

    // Create new user
    const newUser = new User();
    newUser.FirstName = '';
    newUser.LastName = '';
    newUser.Alias = alias;
    newUser.Email = registerDto.email;
    newUser.password = hashedPassword;

    // Save to database
    const savedUser = await this.usersService.save(newUser);

    // Return registration result
    return {
      message: 'User registered successfully',
      user: {
        UserID: savedUser.UserID,
        Email: savedUser.Email,
        Alias: savedUser.Alias,
      },
    };
  }

  async validateUser(username: string, password: string): Promise<User | null> {
    const user = await this.usersService.findByAlias(username);
    if (user && (await bcrypt.compare(password, user.password))) {
      return user;
    }
    return null;
  }

  /**
   * Demonstrates the usage of AuthExceptionFactory
   * Advanced validation method with login attempt limits
   */
  async validateUserWithRateLimit(
    username: string,
    password: string,
  ): Promise<User | null> {
    const user = await this.usersService.findByAlias(username);
    if (!user) {
      return null;
    }

    const isPasswordValid = await bcrypt.compare(password, user.password);
    if (isPasswordValid) {
      // Reset failure count (in real application)
      return user;
    }

    // Simulate getting failed attempt count
    const failedAttempts = this.getFailedLoginAttempts(username);
    const maxAttempts = 5;

    // Use AuthExceptionFactory to create complex exceptions
    throw AuthExceptionFactory.createLoginFailedException(
      failedAttempts + 1,
      maxAttempts,
      30, // Lock for 30 minutes
    );
  }

  /**
   * Mock method: Get failed login attempt count
   * In real applications, this should be retrieved from database or Redis
   */
  private getFailedLoginAttempts(_username: string): number {
    // Return mock data here, in real applications get from storage
    console.log(`Getting failed login attempts for user: ${_username}`);
    return Math.floor(Math.random() * 3); // 0-2 failures
  }

  async login(authDto: AuthDto): Promise<TokenDto> {
    const user = await this.validateUser(authDto.username, authDto.password);
    if (!user) {
      throw AuthExceptions.INVALID_CREDENTIALS();
    }

    const payload = { username: user.Alias, sub: user.UserID };
    const token = this.jwtService.sign(payload);
    const refreshToken = this.jwtService.sign(payload, {
      expiresIn: '7d',
    });

    return {
      token,
      refreshToken,
      status: 200,
    };
  }

  async getCurrentUser(userId: number): Promise<UserDto> {
    const user = await this.usersService.findOne(userId);
    if (!user) {
      throw AuthExceptions.USER_NOT_FOUND();
    }

    // Convert to UserDto format
    return {
      UserID: user.UserID,
      Name: {
        FirstName: user.FirstName,
        LastName: user.LastName,
      },
      Alias: user.Alias,
      Email: user.Email,
    };
  }

  refreshToken(token: string): TokenDto {
    try {
      const decoded: unknown = this.jwtService.verify(token);

      // Use type guard for safe type checking
      if (isValidJwtPayload(decoded)) {
        const payload: JwtPayload = {
          username: decoded.username,
          sub: decoded.sub,
        };

        const newToken = this.jwtService.sign(payload);
        const newRefreshToken = this.jwtService.sign(payload, {
          expiresIn: '7d',
        });

        return {
          token: newToken,
          refreshToken: newRefreshToken,
          status: 200,
        };
      } else {
        throw AuthExceptions.INVALID_TOKEN_FORMAT();
      }
    } catch {
      throw AuthExceptions.INVALID_REFRESH_TOKEN();
    }
  }
}
