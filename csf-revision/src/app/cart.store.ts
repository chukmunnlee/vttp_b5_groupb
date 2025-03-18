import { Injectable } from "@angular/core";
import { ComponentStore } from "@ngrx/component-store";
import { CartSlice, Item } from "./models";

const INIT_STORE: CartSlice = {
  items: [],
  lastUpdate: 0
}

@Injectable()
export class CartStore extends ComponentStore<CartSlice> {

  constructor() { super(INIT_STORE) }

  // reducers/mutators
  // addToCart(newItem: Item)
  readonly addToCart = this.updater<Item>(
    (store: CartSlice, newItem: Item) => {
      // how to add newItem to store
      // you must not update store
      // you must create a new copy with the updates
      return {
        items: [ ...store.items, newItem ],
        lastUpdate: Date.now()
      } as CartSlice
    }
  )

  // deleteItem(string)
  readonly deleteItem = this.updater<string>(
    (store: CartSlice, itemToDelete: string) => {
      return {
        lastUpdate: Date.now(),
        items: store.items.filter(i => i.item != itemToDelete)
      }
    }
  )

  // selectors/queries
  // countItemsInCart(): Observable<number>
  readonly countItemsInCart = this.select<number>(
    (store: CartSlice) => store.items.length
  )

  // Observable<Item[]>
  readonly getItems = this.select<Item[]>(
    (store: CartSlice) => store.items
  )
}
