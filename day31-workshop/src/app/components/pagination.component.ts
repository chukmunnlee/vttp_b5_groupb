import { Component, Input, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { PaginationEvent } from '../models';

@Component({
  selector: 'app-pagination',
  standalone: false,
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.css'
})
export class PaginationComponent {

  @Input()
  currNum = 1

  @Output()
  numUpdated = new Subject<PaginationEvent>()

  step = [ 1, 2, 3, 4, 5 ]

  currStep = 1

  whenChangeStep($event: any) {
    this.currStep = parseInt($event.target.value)
  }

  paginate(dir: number) {
    this.currNum += this.currStep  * dir
    console.info(`currStep: ${this.currStep}, currNum: ${this.currNum}`)
    const event: PaginationEvent = {
      value: this.currNum,
      step: this.currStep
    }
    this.numUpdated.next(event)
  }

}
