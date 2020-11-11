import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap,map } from 'rxjs/operators';
import { Product } from '../common/product';
import { CustomerCart } from '../common/customer-cart';
import { CustomerItem } from '../common/customer-item';
import { Item } from '../common/item';


@Injectable({
  providedIn: 'root'
})
export class ProductItemService {

  private baseUrl ="http://localhost:8989/product-item/getAllProducts";
  private baseSaveUrl ="http://localhost:8989/customer/saveCustomerItemBasket";
  private baseItemBasketUrl ="http://localhost:8989/customer/customerItemBasket?name=admin";
  private baseItemUrl ="http://localhost:8989/product-item/findByItemId?itemId=";

  constructor(private httpClient: HttpClient) { }
  getProductItem():Observable<Product[]>{
    return this.httpClient.get<Product[]>(this.baseUrl).pipe(
      map(response => response)
    );

  }

  getItems(itemId: string):Observable<Item>{
    console.log('id',itemId);
    console.log('url',this.baseItemUrl+itemId);
    return this.httpClient.get<Item>(this.baseItemUrl+itemId).pipe(
      map(response => response)
    );

  }

  getCustomerCart():Observable<CustomerCart>{
    return this.httpClient.get<CustomerCart>(this.baseItemBasketUrl).pipe(
      map(response => response)
    );

  }
  saveCustomerItem(customerItem: Object): Observable<Object> {
    return this.httpClient.post(`${this.baseSaveUrl}`, customerItem);
  }



}




interface GetResponseProduct{
    product:Product[];
}