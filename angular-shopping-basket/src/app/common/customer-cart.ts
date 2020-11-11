import { CustomerItem } from './customer-item';

export class CustomerCart {
    customerName:string = 'admin';
    items:CustomerItem[];
    constructor(customerItem:CustomerItem[]){
        this.items = customerItem;
    }
}
