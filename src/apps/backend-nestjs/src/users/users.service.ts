import {
  Injectable,
  BadRequestException,
  NotFoundException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { User } from './entities/user.entity';

// Export paginated response type
export interface UserListResponse {
  users: User[];
  total: number;
  skip: number;
  limit: number;
}

@Injectable()
export class UsersService {
  constructor(
    @InjectRepository(User)
    private readonly userRepository: Repository<User>,
  ) {}

  async create(createUserDto: CreateUserDto): Promise<User> {
    const user = this.userRepository.create(createUserDto);
    return await this.userRepository.save(user);
  }

  async findAll(limit?: number, skip?: number): Promise<UserListResponse> {
    // Set default values
    const defaultLimit = 10;
    const defaultSkip = 0;

    const actualLimit = limit ?? defaultLimit;
    const actualSkip = skip ?? defaultSkip;

    // Validate parameters
    if (actualLimit < 0 || actualSkip < 0) {
      throw new BadRequestException(
        'Limit and skip must be non-negative numbers',
      );
    }

    // Get paginated data and total count
    const [users, total] = await this.userRepository.findAndCount({
      take: actualLimit,
      skip: actualSkip,
      order: {
        UserID: 'ASC',
      },
    });

    return {
      users,
      total,
      skip: actualSkip,
      limit: actualLimit,
    };
  }

  async findOne(id: number): Promise<User | null> {
    const user = await this.userRepository.findOne({
      where: { UserID: id },
    });
    return user || null;
  }

  async findByEmail(email: string): Promise<User | null> {
    const user = await this.userRepository.findOne({
      where: { Email: email },
    });
    return user || null;
  }

  async findByAlias(alias: string): Promise<User | null> {
    const user = await this.userRepository.findOne({
      where: { Alias: alias },
    });
    return user || null;
  }

  async update(id: number, updateUserDto: UpdateUserDto): Promise<User> {
    const user = await this.findOne(id);
    if (!user) {
      throw new NotFoundException(`User with ID ${id} not found`);
    }

    Object.assign(user, updateUserDto);
    return await this.userRepository.save(user);
  }

  async remove(id: number): Promise<void> {
    const user = await this.findOne(id);
    if (!user) {
      throw new NotFoundException(`User with ID ${id} not found`);
    }

    await this.userRepository.remove(user);
  }

  async save(user: User): Promise<User> {
    return await this.userRepository.save(user);
  }
}
