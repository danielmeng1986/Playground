import {
  Controller,
  Post,
  Body,
  Get,
  UseGuards,
  Request,
  HttpStatus,
  UnauthorizedException,
} from '@nestjs/common';
import {
  ApiTags,
  ApiOperation,
  ApiResponse,
  ApiBearerAuth,
  ApiBody,
} from '@nestjs/swagger';
import { JwtAuthGuard } from './guards/jwt-auth.guard';
import { AuthService } from './auth.service';
import { AuthDto, TokenDto } from './dto/auth.dto';
import { UserDto } from './dto/user.dto';
import { RegisterDto, RegisterResponseDto } from './dto/register.dto';

interface AuthenticatedRequest {
  user: {
    userId: number;
    username: string;
  };
  headers: {
    authorization?: string;
  };
}

@ApiTags('Auth')
@Controller()
export class AuthController {
  constructor(private authService: AuthService) {}

  @Post('auth')
  @ApiOperation({
    summary: 'User login',
    description: 'login with username and password',
    operationId: 'post-login',
  })
  @ApiBody({ type: AuthDto })
  @ApiResponse({
    status: HttpStatus.OK,
    description: 'Login successful',
    type: TokenDto,
  })
  @ApiResponse({
    status: HttpStatus.UNAUTHORIZED,
    description: 'Invalid credentials',
  })
  @ApiResponse({
    status: HttpStatus.INTERNAL_SERVER_ERROR,
    description: 'Server Error',
  })
  login(@Body() authDto: AuthDto): Promise<TokenDto> {
    return this.authService.login(authDto);
  }

  @Post('auth/register')
  @ApiOperation({
    summary: 'Register new account',
    description: 'register account',
    operationId: 'post-auth-register',
  })
  @ApiBody({ type: RegisterDto })
  @ApiResponse({
    status: HttpStatus.OK,
    description: 'Registration successful',
    type: RegisterResponseDto,
  })
  @ApiResponse({
    status: HttpStatus.CONFLICT,
    description: 'Email already registered',
  })
  @ApiResponse({
    status: HttpStatus.BAD_REQUEST,
    description: 'Invalid input data',
  })
  @ApiResponse({
    status: HttpStatus.INTERNAL_SERVER_ERROR,
    description: 'Server Error',
  })
  register(@Body() registerDto: RegisterDto): Promise<RegisterResponseDto> {
    return this.authService.register(registerDto);
  }

  @Get('auth/me')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({
    summary: 'Get current user info',
    description: 'Get current user',
    operationId: 'get-current-user',
  })
  @ApiResponse({
    status: HttpStatus.OK,
    description: 'Current user info',
    type: UserDto,
  })
  @ApiResponse({
    status: HttpStatus.UNAUTHORIZED,
    description: 'Unauthorized',
  })
  @ApiResponse({
    status: HttpStatus.INTERNAL_SERVER_ERROR,
    description: 'Server Error',
  })
  getCurrentUser(@Request() req: AuthenticatedRequest): UserDto {
    return this.authService.getCurrentUser(req.user.userId);
  }

  @Post('refresh')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({
    summary: 'Refresh JWT token',
    description: 'Refresh token with rToken',
    operationId: 'post-refresh-token',
  })
  @ApiResponse({
    status: HttpStatus.OK,
    description: 'Token refreshed',
    type: TokenDto,
  })
  @ApiResponse({
    status: HttpStatus.UNAUTHORIZED,
    description: 'Invalid refresh token',
  })
  @ApiResponse({
    status: HttpStatus.INTERNAL_SERVER_ERROR,
    description: 'Server Error',
  })
  refreshToken(@Request() req: AuthenticatedRequest): TokenDto {
    const authHeader = req.headers.authorization;
    if (!authHeader) {
      throw new UnauthorizedException('Authorization header not found');
    }
    const token = authHeader.replace('Bearer ', '');
    return this.authService.refreshToken(token);
  }
}
