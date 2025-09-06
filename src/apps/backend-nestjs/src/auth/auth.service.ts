import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { AuthDto, TokenDto } from './dto/auth.dto';
import { UserDto } from './dto/user.dto';
import { RegisterDto, RegisterResponseDto } from './dto/register.dto';
import {
  AuthExceptions,
  AuthExceptionFactory,
} from './exceptions/auth.exceptions';
import * as bcrypt from 'bcrypt';

interface UserEntity {
  UserID: number;
  Name: { FirstName: string; LastName: string };
  Alias: string;
  Email: string;
  password: string;
}

interface JwtPayload {
  username: string;
  sub: number;
  iat?: number;
  exp?: number;
}

// 类型守卫函数
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
  constructor(private jwtService: JwtService) {}

  // 模拟用户数据库
  private readonly users: UserEntity[] = [];

  async register(registerDto: RegisterDto): Promise<RegisterResponseDto> {
    // 检查邮箱是否已存在
    const existingUser = this.users.find((u) => u.Email === registerDto.email);
    if (existingUser) {
      throw AuthExceptions.EMAIL_EXISTS(registerDto.email);
    }

    // ✅ 400 验证异常（如果需要）
    if (!registerDto.email || !registerDto.password) {
      throw AuthExceptions.MISSING_CREDENTIALS();
    }

    // 生成新的用户ID
    const newUserId = Math.max(...this.users.map((u) => u.UserID)) + 1;

    // 生成用户别名（从邮箱提取）
    const alias = registerDto.email.split('@')[0];

    // 加密密码
    const saltRounds = 10;
    const hashedPassword = await bcrypt.hash(registerDto.password, saltRounds);

    // 创建新用户
    const newUser: UserEntity = {
      UserID: newUserId,
      Name: { FirstName: '', LastName: '' }, // 注册时暂时为空，后续可以让用户填写
      Alias: alias,
      Email: registerDto.email,
      password: hashedPassword,
    };

    // 添加到用户数组
    this.users.push(newUser);

    // 返回注册结果
    return {
      message: 'User registered successfully',
      user: {
        UserID: newUser.UserID,
        Email: newUser.Email,
        Alias: newUser.Alias,
      },
    };
  }

  async validateUser(
    username: string,
    password: string,
  ): Promise<UserEntity | null> {
    const user = this.users.find((u) => u.Alias === username);
    if (user && (await bcrypt.compare(password, user.password))) {
      return user;
    }
    return null;
  }

  /**
   * 演示 AuthExceptionFactory 的使用场景
   * 带有登录尝试限制的高级验证方法
   */
  async validateUserWithRateLimit(
    username: string,
    password: string,
  ): Promise<UserEntity | null> {
    const user = this.users.find((u) => u.Alias === username);
    if (!user) {
      return null;
    }

    const isPasswordValid = await bcrypt.compare(password, user.password);
    if (isPasswordValid) {
      // 重置失败计数（实际应用中）
      return user;
    }

    // 模拟获取失败尝试次数
    const failedAttempts = this.getFailedLoginAttempts(username);
    const maxAttempts = 5;

    // 使用 AuthExceptionFactory 创建复杂的异常
    throw AuthExceptionFactory.createLoginFailedException(
      failedAttempts + 1,
      maxAttempts,
      30, // 锁定30分钟
    );
  }

  /**
   * 模拟方法：获取失败登录尝试次数
   * 实际应用中应该从数据库或 Redis 中获取
   */
  private getFailedLoginAttempts(_username: string): number {
    // 这里返回模拟数据，实际应用中从存储中获取
    console.log(`Getting failed login attempts for user: ${_username}`);
    return Math.floor(Math.random() * 3); // 0-2 次失败
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

  getCurrentUser(userId: number): UserDto {
    const user = this.users.find((u) => u.UserID === userId);
    if (!user) {
      throw AuthExceptions.USER_NOT_FOUND();
    }

    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const { password: _, ...result } = user;
    return result as UserDto;
  }

  refreshToken(token: string): TokenDto {
    try {
      const decoded: unknown = this.jwtService.verify(token);

      // 使用类型守卫进行安全的类型检查
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
