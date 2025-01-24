import axios from 'axios';

export interface EurekaInstance {
  instanceId: string;
  hostName: string;
  app: string;
  ipAddr: string;
  status: string;
  overriddenStatus?: string;
  port: { $: number; '@enabled': boolean };
  securePort?: { $: number; '@enabled': boolean };
  countryId: number;
  dataCenterInfo: {
    '@class': string;
    name: string;
  };
  leaseInfo?: {
    renewalIntervalInSecs: number;
    durationInSecs: number;
    registrationTimestamp: number;
    lastRenewalTimestamp: number;
    evictionTimestamp: number;
    serviceUpTimestamp: number;
  };
  metadata?: Record<string, string>;
  homePageUrl?: string;
  statusPageUrl?: string;
  healthCheckUrl?: string;
  vipAddress?: string;
  secureVipAddress?: string;
}

export interface EurekaApplication {
  name: string;
  instance: EurekaInstance[] | EurekaInstance;
}

export interface EurekaResponse {
  application: EurekaApplication;
}

export class EurekaUtils {
  private static readonly eurekaServer = 'http://discovery-server:8761/eureka';

  static async getServiceUrl(serviceName: string): Promise<string> {
    try {
      const response = await axios.get<EurekaResponse>(
        `${this.eurekaServer}/apps/${serviceName.toUpperCase()}`,
        { headers: { Accept: 'application/json' } },
      );

      const instances = Array.isArray(response.data.application.instance)
        ? response.data.application.instance
        : [response.data.application.instance];

      const instance: EurekaInstance = instances[0];
      const { ipAddr, port } = instance;

      return `http://${ipAddr}:${port.$}`;
    } catch (error) {
      console.error(`Failed to get service URL for ${serviceName}:`, error);
      throw new Error(`Service ${serviceName} not found`);
    }
  }
}
