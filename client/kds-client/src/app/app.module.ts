import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderComponent } from './components/shared/order/order.component';
import { OrderInfoComponent } from './components/shared/order-info/order-info.component';
import { ButtonComponent } from './components/shared/button/button.component';

@NgModule({
  declarations: [
    AppComponent,
    OrderComponent,
    OrderInfoComponent,
    ButtonComponent,
  ],
  imports: [BrowserModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
