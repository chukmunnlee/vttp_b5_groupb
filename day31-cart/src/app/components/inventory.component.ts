import { Component, Input, Output } from '@angular/core';
import {Inventory, UpdateItemEvent} from '../models';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-inventory',
  standalone: false,
  templateUrl: './inventory.component.html',
  styleUrl: './inventory.component.css'
})
export class InventoryComponent {

  @Input()
  inventories: Inventory[] = []

  @Output()
  whenItemUpdate = new Subject<UpdateItemEvent>();

  //updateItem(idx: number, delta: number) {
  updateItem(key: string, delta: number) {
    console.info(`key: ${key}, delta: ${delta}`)
    const event: UpdateItemEvent = {
      key, delta
      //key: key,
      //delta: delta
    }
    this.whenItemUpdate.next(event)
  }

}
