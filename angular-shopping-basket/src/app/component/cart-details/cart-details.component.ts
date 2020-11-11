import { Component, OnInit, Inject } from '@angular/core';
import { CustomerItem } from 'src/app/common/customer-item';
import { CartService } from 'src/app/services/cart.service';
import { DOCUMENT } from '@angular/common';
import { CustomerCart } from 'src/app/common/customer-cart';
import { ProductItemService } from 'src/app/services/product-item.service';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {
  customerItems: CustomerItem[] = [];
  customerCart:CustomerCart = new CustomerCart(this.customerItems);
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private _cartService: CartService,
    @Inject(DOCUMENT) private document: Document ) { }

  ngOnInit(): void  {
    this.cartDetails();
  }
  

  cartDetails() {
    //subscribe to the events
    
    console.log(this._cartService);
    this.customerItems = this._cartService.customerItem;
    console.log(this.customerItems);
    this._cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    this._cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

    this._cartService.calculateTotalPrice();
  }

  incrementQuantity(customerItems: CustomerItem){
    this._cartService.addToCart(customerItems);
  }

  decreQuantity(customerItems: CustomerItem){
    this._cartService.decreQuantity(customerItems);
  }

  saveBasket(customerItems: CustomerItem[]){
 
    this.customerCart = new CustomerCart(customerItems);
    console.log('cart obj',this.customerCart );
    this._cartService.save(  this.customerCart);
    this.document.location.reload();
  }

  remove(customerItems: CustomerItem){
    customerItems.quantity=0;
    this._cartService.remove(customerItems);
  }

}
