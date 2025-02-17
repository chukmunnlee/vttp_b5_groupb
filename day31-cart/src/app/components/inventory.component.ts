import { Component, Input } from '@angular/core';
import {Inventory} from '../models';

@Component({
  selector: 'app-inventory',
  standalone: false,
  templateUrl: './inventory.component.html',
  styleUrl: './inventory.component.css'
})
export class InventoryComponent {

  @Input()
  inventories: Inventory[] = []

}
