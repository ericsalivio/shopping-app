import { Component, OnInit } from '@angular/core';
import { ProductItemService } from 'src/app/services/product-item.service';
import { Product } from 'src/app/common/product';
import { CartService } from 'src/app/services/cart.service';
import { CustomerItem } from 'src/app/common/customer-item';
import { Item } from 'src/app/common/item';

@Component({
  selector: 'app-product-items-list',
  templateUrl: './product-items-list-grid.component.html',
  styleUrls: ['./product-items-list.component.css']
})
export class ProductItemsListComponent implements OnInit {

  products:Product[];

  constructor(private _productItem: ProductItemService,
    private _cartService:CartService) { }

  ngOnInit(): void {
    this.listProducts();
  
   
  }

 

  listProducts(){
    this._productItem.getProductItem().subscribe(
      data => {
        this.products = data
      } 
    )
  }

addToCart(item:Item){
  console.log(`item name: ${item.name}, andd price: ${item.price}`);
  item.quantity = 1;
  const customerItem = new CustomerItem(item);
  this._cartService.addToCart(customerItem);
}

}
