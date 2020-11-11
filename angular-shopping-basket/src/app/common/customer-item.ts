import { Item } from './item';

export class CustomerItem {
    itemId:string;
    quantity:number;
    price:number;
    imageUrl:string;
    name:string;
    description:string;
    constructor(item:Item){
        this.itemId =item.itemId;
        this.price = item.price;
        this.quantity =  item.quantity;
        this.imageUrl = item.imageUrl;
        this.name = item.name;
        this.description = item.description;
    }
}
