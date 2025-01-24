import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AuthService } from './auth.service';
import { TokenService } from './token.service';
import { JwtService } from '@nestjs/jwt';
import { EurekaServiceDestroy, EurekaService } from './libs';
import { ConfigModule } from '@nestjs/config';

@Module({
  imports: [ConfigModule.forRoot()],
  controllers: [AppController],
  providers: [
    AppService,
    EurekaService,
    EurekaServiceDestroy,
    AuthService,
    TokenService,
    JwtService,
  ],
})
export class AppModule {}
