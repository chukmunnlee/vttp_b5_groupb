import { Component, Input } from '@angular/core';
import {Cart} from '../models';

@Component({
  selector: 'app-cart',
  standalone: false,
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  @Input({ required: true })
  cart!: Cart

}
