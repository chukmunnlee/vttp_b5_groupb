import { Component } from '@angular/core';
import {Cart, INVENTORY, LineItem, UpdateItemEvent} from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {

  inventories = INVENTORY
  cart: Cart = { lineItems: [], total: 0 }

  itemUpdated($event: UpdateItemEvent) {
    console.info('>>>> ', $event)
    // find the item
    var idx = this.cart.lineItems.findIndex(li => li.key == $event.key)
    if (idx >= 0) {
      this.cart.lineItems[idx].quantity += $event.delta
      this.cart.lineItems[idx].total = this.cart.lineItems[idx].unitPrice * this.cart.lineItems[idx].quantity

    } else if ($event.delta > 0) {
      idx = this.inventories.findIndex(inv => inv.key == $event.key)
      const lineItem: LineItem = {
        key: this.inventories[idx].key,
        name: this.inventories[idx].name,
        unitPrice: this.inventories[idx].unitPrice,
        quantity: 1,
        total: this.inventories[idx].unitPrice
      }
      this.cart.lineItems.push(lineItem)
    }

    // Remove 0 items
    this.cart.lineItems = this.cart.lineItems.filter(li => li.quantity > 0)
    let total = 0
    for (let li of this.cart.lineItems)
      total += li.quantity * li.unitPrice
    this.cart.total = total
  }
}
