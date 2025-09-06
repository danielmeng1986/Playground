import {
  BadRequestException,
  ConflictException,
  ForbiddenException,
  HttpException,
  HttpStatus,
  NotFoundException,
  UnauthorizedException,
} from '@nestjs/common';

/**
 * Custom TooManyRequestsException
 */
export class TooManyRequestsException extends HttpException {
  constructor(message: string = 'Too many requests', description?: string) {
    super(
      {
        message,
        error: 'Too Many Requests',
        statusCode: HttpStatus.TOO_MANY_REQUESTS,
        ...(description && { description }),
      },
      HttpStatus.TOO_MANY_REQUESTS,
    );
  }
}

/**
 * Centralized management of authentication-related exceptions
 */
export const AuthExceptions = {
  // Authentication failures
  INVALID_CREDENTIALS: (message: string = 'Invalid credentials') =>
    new UnauthorizedException(message),

  INVALID_PASSWORD: (message: string = 'Invalid password') =>
    new UnauthorizedException(message),

  INVALID_TOKEN: (message: string = 'Invalid token') =>
    new UnauthorizedException(message),

  INVALID_TOKEN_FORMAT: (message: string = 'Invalid token format') =>
    new UnauthorizedException(message),

  TOKEN_EXPIRED: (message: string = 'Token has expired') =>
    new UnauthorizedException(message),

  INVALID_REFRESH_TOKEN: (message: string = 'Invalid refresh token') =>
    new UnauthorizedException(message),

  AUTHENTICATION_REQUIRED: (message: string = 'Authentication required') =>
    new UnauthorizedException(message),

  // Insufficient permissions
  INSUFFICIENT_PERMISSIONS: (message: string = 'Insufficient permissions') =>
    new ForbiddenException(message),

  ACCESS_DENIED: (message: string = 'Access denied') =>
    new ForbiddenException(message),

  ROLE_REQUIRED: (role: string) =>
    new ForbiddenException(`Role '${role}' is required`),

  // User-related exceptions
  USER_NOT_FOUND: (message: string = 'User not found') =>
    new NotFoundException(message),

  EMAIL_EXISTS: (email?: string) =>
    new ConflictException(
      email ? `Email '${email}' already exists` : 'Email already registered',
    ),

  USERNAME_EXISTS: (username?: string) =>
    new ConflictException(
      username
        ? `Username '${username}' already exists`
        : 'Username already exists',
    ),

  USER_ALREADY_VERIFIED: (message: string = 'User is already verified') =>
    new ConflictException(message),

  USER_NOT_VERIFIED: (message: string = 'User is not verified') =>
    new ForbiddenException(message),

  ACCOUNT_LOCKED: (message: string = 'Account is locked') =>
    new ForbiddenException(message),

  ACCOUNT_DISABLED: (message: string = 'Account is disabled') =>
    new ForbiddenException(message),

  // Request parameter errors
  MISSING_CREDENTIALS: (message: string = 'Email and password are required') =>
    new BadRequestException(message),

  INVALID_EMAIL_FORMAT: (message: string = 'Invalid email format') =>
    new BadRequestException(message),

  WEAK_PASSWORD: (message: string = 'Password does not meet requirements') =>
    new BadRequestException(message),

  PASSWORD_MISMATCH: (message: string = 'Passwords do not match') =>
    new BadRequestException(message),

  // Rate limiting
  TOO_MANY_LOGIN_ATTEMPTS: (
    message: string = 'Too many login attempts, please try again later',
  ) => new TooManyRequestsException(message),

  TOO_MANY_REGISTRATION_ATTEMPTS: (
    message: string = 'Too many registration attempts, please try again later',
  ) => new TooManyRequestsException(message),

  RATE_LIMIT_EXCEEDED: (message: string = 'Rate limit exceeded') =>
    new TooManyRequestsException(message),
} as const;

/**
 * Authentication exception factory class - for complex exception scenarios
 */
export class AuthExceptionFactory {
  /**
   * Create login failure exception with detailed information
   */
  static createLoginFailedException(
    attempts: number,
    maxAttempts: number,
    lockoutDuration?: number,
  ): UnauthorizedException | TooManyRequestsException {
    if (attempts >= maxAttempts) {
      const message = lockoutDuration
        ? `Account locked for ${lockoutDuration} minutes due to too many failed attempts`
        : 'Account locked due to too many failed attempts';
      return new TooManyRequestsException(message);
    }

    const remainingAttempts = maxAttempts - attempts;
    return new UnauthorizedException(
      `Invalid credentials. ${remainingAttempts} attempts remaining before account lock.`,
    );
  }

  /**
   * Create token expiration exception with expiry time
   */
  static createTokenExpiredException(expiredAt?: Date): UnauthorizedException {
    const message = expiredAt
      ? `Token expired at ${expiredAt.toISOString()}`
      : 'Token has expired';
    return new UnauthorizedException(message);
  }

  /**
   * Create insufficient permissions exception with required permission info
   */
  static createInsufficientPermissionsException(
    required: string[],
    current: string[],
  ): ForbiddenException {
    const missing = required.filter((perm) => !current.includes(perm));
    return new ForbiddenException(
      `Missing required permissions: ${missing.join(', ')}`,
    );
  }
}
