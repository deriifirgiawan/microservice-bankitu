import { IsNotEmpty, IsString } from 'class-validator';

export class RegisterRequestCreateAccountDto {
  @IsString()
  @IsNotEmpty()
  account_type: string;

  @IsString()
  @IsNotEmpty()
  saldo: string;
}

export class RegisterRequestDto {
  @IsString()
  @IsNotEmpty()
  fullname: string;

  @IsString()
  @IsNotEmpty()
  email: string;

  @IsString()
  @IsNotEmpty()
  pin: string;

  @IsString()
  @IsNotEmpty()
  phone_number: string;

  @IsString()
  @IsNotEmpty()
  date_of_birth: string;

  @IsString()
  @IsNotEmpty()
  gender: string;

  @IsString()
  @IsNotEmpty()
  complete_address: string;

  create_account: RegisterRequestCreateAccountDto;
}
