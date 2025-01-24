import { Injectable, OnModuleDestroy } from '@nestjs/common';
import axios from 'axios';

@Injectable()
export class EurekaServiceDestroy implements OnModuleDestroy {
  private readonly eurekaServer = 'http://discovery-server:8761/eureka';
  private readonly instanceId = 'auth-service:127.0.0.1:3000';

  async onModuleDestroy() {
    try {
      await axios.delete(
        `${this.eurekaServer}/apps/AUTH-SERVICE/${this.instanceId}`,
      );
      console.log('Successfully unregistered from Eureka');
    } catch (error) {
      console.error('Failed to unregister from Eureka:', error);
    }
  }
}
