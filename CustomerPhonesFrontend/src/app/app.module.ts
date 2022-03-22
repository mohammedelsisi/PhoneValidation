import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { CustomerPhoneSearchComponent } from './customer-phone-search/customer-phone-search.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerPhoneSearchComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgbModule
    

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
