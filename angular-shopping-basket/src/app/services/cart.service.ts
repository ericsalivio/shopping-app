import { Injectable } from '@angular/core';
import { CustomerCart } from '../common/customer-cart';
import { Subject } from 'rxjs';
import { CustomerItem } from '../common/customer-item';
import { HttpClient } from '@angular/common/http';
import { ProductItemService } from './product-item.service';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  

  products: Product[];

  customerItem: CustomerItem[] = [];
  customerCart: CustomerCart;
  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor(private _productItem: ProductItemService) {

  }


  addToCartInitial(customerCart: CustomerCart) {
    console.log('exist item cart', customerCart);

    this._productItem.getCustomerCart().subscribe(
      data => {
        this.customerCart = data;
      } 
    )
    if (customerCart != null) {
      for (let cartItem of customerCart.items) {
        console.log(' quantity',cartItem.quantity);
        this._productItem.getItems(cartItem.itemId).subscribe(
          data => {
            console.log(' item cart', data.quantity = cartItem.quantity);
         
            console.log(' item cart data', data);
            let customerItem = new CustomerItem(data);
            console.log(' item cart data', customerItem);
            this.addToCart(customerItem);
          }
        )
      }

    }

  }

  addToCart(theCustomerItem: CustomerItem) {
    console.log('customerItem',theCustomerItem);
    let alreadyExistInCart: boolean = false;
    let existingCartItem: CustomerItem = undefined;

    if (this.customerItem.length > 0) {
      existingCartItem = this.customerItem.find(tempCustomerItem => tempCustomerItem.itemId === theCustomerItem.itemId);
      alreadyExistInCart = (existingCartItem != undefined)
      console.log(existingCartItem);
      console.log(alreadyExistInCart);
    }
    if (alreadyExistInCart) {
      existingCartItem.quantity++;
    } else {
      this.customerItem.push(theCustomerItem);
    }
    this.calculateTotalPrice();
  }
  calculateTotalPrice() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (let currentCustomerItem of this.customerItem) {
      console.log(currentCustomerItem.quantity);
      totalPriceValue += currentCustomerItem.quantity * currentCustomerItem.price;
      totalQuantityValue += currentCustomerItem.quantity;
    }
    console.log(`total price:${totalPriceValue}, total quantity: ${totalQuantityValue}`);
    //publish event
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

  }

  decreQuantity(item: CustomerItem) {
  
    console.log(item);
    if (item.quantity != 0) {
      item.quantity--;
    } 
      this.calculateTotalPrice();
    


  }

  remove(item: CustomerItem) {
    console.log(item);
    item.quantity=0;
    this.calculateTotalPrice();
    // const itemIndex = this.customerItem.findIndex((tempItem) => tempItem.itemId === item.itemId);
    // if (itemIndex > -1) {
    //   this.customerItem.splice(itemIndex, 1);
    //   this.calculateTotalPrice();
    // }
  }

  save(customerCart: CustomerCart) {
    console.log('saving data:', customerCart);
    this._productItem
      .saveCustomerItem(customerCart).subscribe(data => {
      },
        error => console.log(error));
  }
}
