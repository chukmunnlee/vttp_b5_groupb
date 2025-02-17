import { Component } from '@angular/core';
import { PaginationEvent } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {

  imgNum = 4

  updateNum($event: PaginationEvent) {

    console.info('pagination event: ', $event)

    var value = $event.value
    if (value > 30)
      value = 0
    else if (value <0)
      value = 30

    this.imgNum = value
  }
}
