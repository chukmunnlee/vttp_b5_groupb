import { Component, Input, Output } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-text-input',
  standalone: false,
  templateUrl: './text-input.component.html',
  styleUrl: './text-input.component.css'
})
export class TextInputComponent {

  @Input()
  count = 0

  @Output()
  newNumber = new Subject<number>()

  message = "hello"

  delta = 1

  steps = [ 1, 2, 3, 4, 5 ]

  updateCount(delta: number) {
    this.count += delta
    // fire the onNewNumber event
    this.newNumber.next(this.count)
  }

  // Object -> any
  updateStep($event: any | null) {
    console.info('select updated: ', $event.value)
    //console.info('select updated: ', $event.target.value)
    // values from HTML controls are string
    //this.delta = parseInt($event.target.value)
    this.delta = parseInt($event.value)
  }

  incrementCount() {
    console.info('count: ', this.count)
    this.count++
  }
  decrementCount() {
    console.info('count: ', this.count)
    this.count--
  }

}
