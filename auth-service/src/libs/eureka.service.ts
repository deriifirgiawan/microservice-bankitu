import { Injectable, OnModuleInit } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import axios from 'axios';

@Injectable()
export class EurekaService implements OnModuleInit {
  private readonly eurekaServer: string;
  private readonly instanceConfig: any;

  constructor(private readonly configService: ConfigService) {
    this.eurekaServer =
      this.configService.get<string>('EUREKA_SERVER') ??
      'http://localhost:8761/eureka';
    const hostName = this.configService.get<string>(
      'HOST_NAME',
      'auth-service',
    );
    const ipAddr = this.configService.get<string>('IP_ADDRESS', '127.0.0.1');
    const port = this.configService.get<number>('PORT', 3000);

    this.instanceConfig = {
      instance: {
        app: 'AUTH-SERVICE',
        hostName,
        ipAddr,
        port: { $: port, '@enabled': true },
        vipAddress: 'auth-service',
        statusPageUrl: `http://${hostName}:${port}/info`,
        healthCheckUrl: `http://${hostName}:${port}/health`,
        dataCenterInfo: {
          '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
          name: 'MyOwn',
        },
      },
    };
  }

  async onModuleInit() {
    try {
      await axios.post(
        `${this.eurekaServer}/apps/AUTH-SERVICE`,
        this.instanceConfig,
        {
          headers: { 'Content-Type': 'application/json' },
        },
      );
      console.log('Successfully registered to Eureka');
    } catch (error) {
      console.error('Failed to register to Eureka:', error);
    }
  }
}
