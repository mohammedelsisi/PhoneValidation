import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import {
  CountryResourceService,
  CustomerDtoPage,
} from '../service/phone-validation';
import { CustomerResourceService } from '../service/phone-validation/api/customerResource.service';
import { CustomerDto } from '../service/phone-validation/model/customerDto';

@Component({
  selector: 'app-customer-phone-search',
  templateUrl: './customer-phone-search.component.html',
  styleUrls: ['./customer-phone-search.component.css'],
})
export class CustomerPhoneSearchComponent implements OnInit {
  selectedState: any = undefined;
  selectedCountry: any = 'Select All';
  pageNumber: number = 0;
  result: CustomerDto[] = [];
  countries: string[] = [];
  numberOfPages = Array();
  constructor(
    private customerResourceService: CustomerResourceService,
    private countryResource: CountryResourceService
  ) {}

  ngOnInit(): void {
    this.getCustomers(this.pageNumber, undefined, undefined);
    this.getCountries();
  }

  getCountries() {
    this.countryResource.getAvailableCountriesUsingGET().subscribe((result) => {
      this.countries = Array.from(result);
      this.countries.unshift(this.selectedCountry);
    });
  }

  getCustomers(pageNumber: number, state: any, country: any) {
    this.customerResourceService
      .getCustomersUsingGET(pageNumber, state, country)
      .subscribe((result) => {
        console.log(result);
        this.result = result.customerDtoList as CustomerDto[];
        const s = result.numberOfPages as number;
        this.numberOfPages = Array(s);
      });
  }

  updatePageNumber(pageNumber: number) {
    this.updateState(pageNumber, this.selectedState, this.selectedCountry);
  }

  updateState(pageNumber: number, state: any, country: any) {
    this.selectedState = state;
    this.pageNumber = pageNumber;
    this.selectedCountry = country;
    if (country == 'Select All') country = undefined;
    if (state == 'undefined')  state = undefined;

    this.getCustomers(pageNumber, state, country);
  }
}
