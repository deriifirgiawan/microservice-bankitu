import { Body, Controller, Get, Post } from '@nestjs/common';
import { AppService } from './app.service';
import { LoginRequestDto, LoginResponseDto, RegisterRequestDto } from './dto';
import { BaseResponse, UserResponse } from './response';
import { AuthService } from './auth.service';

@Controller()
export class AppController {
  constructor(
    private readonly appService: AppService,
    private readonly authService: AuthService,
  ) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Post('/auth/login')
  async login(
    @Body() payload: LoginRequestDto,
  ): Promise<BaseResponse<LoginResponseDto>> {
    const token = await this.authService.login(payload);

    return {
      status: 200,
      message: 'Success Login',
      data: {
        ...token,
      },
    };
  }

  @Post('/auth/register')
  async register(
    @Body() payload: RegisterRequestDto,
  ): Promise<BaseResponse<UserResponse>> {
    const response = await this.authService.register(payload);

    return {
      status: 201,
      message: 'Success Register',
      data: { ...response },
    };
  }
}
