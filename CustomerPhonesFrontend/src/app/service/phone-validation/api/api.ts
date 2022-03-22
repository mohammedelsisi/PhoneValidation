export * from './basicErrorController.service';
import { BasicErrorControllerService } from './basicErrorController.service';
export * from './countryResource.service';
import { CountryResourceService } from './countryResource.service';
export * from './customerResource.service';
import { CustomerResourceService } from './customerResource.service';
export const APIS = [BasicErrorControllerService, CountryResourceService, CustomerResourceService];
