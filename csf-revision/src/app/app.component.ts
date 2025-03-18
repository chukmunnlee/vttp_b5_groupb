import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CartStore } from './cart.store';
import { Item } from './models';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  private fb = inject(FormBuilder)
  private cartStore = inject(CartStore)

  protected form!: FormGroup
  protected itemCount$!: Observable<number>
  protected items$!: Observable<Item[]>

  ngOnInit(): void {
    this.form = this.createForm()
    this.itemCount$ = this.cartStore.countItemsInCart
    this.items$ = this.cartStore.getItems
  }

  deleteItemFromCart() {
    this.cartStore.deleteItem('apple')
  }

  processForm() {
    const item: Item = this.form.value
    console.info('>>> item: ', item)
    this.cartStore.addToCart(item)
    this.form = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      item: this.fb.control<string>(''),
      price: this.fb.control<number>(1),
    })
  }
}
