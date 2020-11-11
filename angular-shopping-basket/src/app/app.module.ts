import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { ProductItemsListComponent } from './component/product-items-list/product-items-list.component';
import { ProductItemService } from './services/product-item.service';
import { CartStatusComponent } from './component/cart-status/cart-status.component';
import { CartDetailsComponent } from './component/cart-details/cart-details.component';



const routes: Routes = [
  { path: 'product-items-list-grid', component: ProductItemsListComponent },
  { path: 'cart-details', component: CartDetailsComponent },
  { path: '', redirectTo: '/product-items-list-grid', pathMatch: 'full' }
];


@NgModule({
  declarations: [
    AppComponent,
    ProductItemsListComponent,
    CartStatusComponent,
    CartDetailsComponent
  
  ],
  imports: [
    BrowserModule,CommonModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    ProductItemService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
