import { BadRequestException, Injectable } from '@nestjs/common';
import { LoginRequestDto, LoginResponseDto, RegisterRequestDto } from './dto';
import { TokenService } from './token.service';
import { EurekaUtils } from './utils';
import axios, { AxiosResponse } from 'axios';
import { UserResponse } from './response';
import { generateHashPassword, validatePassword } from './utils/bcrypt.util';

@Injectable()
export class AuthService {
  constructor(private tokenService: TokenService) {}

  async login(payload: LoginRequestDto): Promise<LoginResponseDto> {
    try {
      const userServiceUrl = await EurekaUtils.getServiceUrl('USER-SERVICE');
      const response = await axios.post<AxiosResponse<UserResponse>>(
        `${userServiceUrl}/users/validate?email=${payload.email}`,
      );

      if (!response.data && response.data === '') {
        throw new BadRequestException('tidak valid');
      }

      if (await validatePassword(response.data.data.pin, payload.pin)) {
        throw new BadRequestException('Wrong Pin');
      }

      const token = await this.tokenService.signToken(1, payload.email);

      return {
        token: token,
      };
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  async register(payload: RegisterRequestDto): Promise<UserResponse> {
    try {
      const user = {
        ...payload,
      };

      user.pin = await generateHashPassword(payload.pin);

      const userServiceUrl = await EurekaUtils.getServiceUrl('USER-SERVICE');
      const response = await axios.post<AxiosResponse<UserResponse>>(
        `${userServiceUrl}/users/create`,
        user,
      );

      if (!response.data && response.data === '') {
        throw new BadRequestException('Email is already registered');
      }

      return response.data.data;
    } catch (error) {
      console.log(error);
      throw error;
    }
  }
}
