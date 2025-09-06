import { ApiProperty } from '@nestjs/swagger';

export class UserNameDto {
  @ApiProperty({
    description: 'First name of the user',
    example: 'John',
  })
  FirstName: string;

  @ApiProperty({
    description: 'Last name of the user',
    example: 'Doe',
  })
  LastName: string;
}

export class UserDto {
  @ApiProperty({
    description: 'Unique user identifier',
    example: 1,
  })
  UserID: number;

  @ApiProperty({
    description: 'User name object containing first and last name',
  })
  Name: UserNameDto;

  @ApiProperty({
    description: 'User alias/username',
    example: 'john_doe',
  })
  Alias: string;

  @ApiProperty({
    description: 'User email address',
    example: 'john.doe@example.com',
  })
  Email: string;
}
