import 'dotenv/config';
import { env } from 'process';
import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';

@Injectable()
export class TokenService {
  constructor(private jwt: JwtService) {}

  async signToken(id: number, email: string): Promise<string> {
    const payload = { id, email };
    const token = await this.jwt.signAsync(payload, { secret: env.JWT_SECRET });

    return token;
  }
}
