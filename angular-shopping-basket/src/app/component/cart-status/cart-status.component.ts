import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';
import { ProductItemService } from 'src/app/services/product-item.service';

@Component({
  selector: 'app-cart-status',
  templateUrl: './cart-status.component.html',
  styleUrls: ['./cart-status.component.css']
})
export class CartStatusComponent implements OnInit {

  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private _cartService: CartService,private _productItem: ProductItemService) { }

  ngOnInit(): void {
    this.updateCartStatus();
    this.listExistCart();
  }
  listExistCart(){
    this._productItem.getCustomerCart().subscribe(
      data => {
        console.log(data);
       this._cartService.addToCartInitial(data);
      } 
    )
  }
  updateCartStatus() {
    this._cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    )

    this._cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    )
  }
}
