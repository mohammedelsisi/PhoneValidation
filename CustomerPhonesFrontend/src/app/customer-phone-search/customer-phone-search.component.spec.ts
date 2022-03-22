import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerPhoneSearchComponent } from './customer-phone-search.component';

describe('CustomerPhoneSearchComponent', () => {
  let component: CustomerPhoneSearchComponent;
  let fixture: ComponentFixture<CustomerPhoneSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerPhoneSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerPhoneSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
