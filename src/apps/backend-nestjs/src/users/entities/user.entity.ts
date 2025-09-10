import {
  Entity,
  Column,
  PrimaryGeneratedColumn,
  CreateDateColumn,
  UpdateDateColumn,
} from 'typeorm';

@Entity('users')
export class User {
  @PrimaryGeneratedColumn()
  UserID: number;

  @Column({ type: 'varchar', length: 100, default: '' })
  FirstName: string;

  @Column({ type: 'varchar', length: 100, default: '' })
  LastName: string;

  @Column({ type: 'varchar', length: 50, unique: true })
  Alias: string;

  @Column({ type: 'varchar', length: 255, unique: true })
  Email: string;

  @Column({ type: 'varchar', length: 255 })
  password: string;

  @CreateDateColumn()
  createdAt: Date;

  @UpdateDateColumn()
  updatedAt: Date;

  // Getter for full name
  get fullName(): string {
    return `${this.FirstName} ${this.LastName}`.trim();
  }

  // Getter for Name object (for compatibility with existing interfaces)
  get Name(): { FirstName: string; LastName: string } {
    return {
      FirstName: this.FirstName,
      LastName: this.LastName,
    };
  }
}
