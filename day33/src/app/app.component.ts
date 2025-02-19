import { AfterContentInit, AfterViewInit, Component, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { NumInputComponent } from './components/num-input.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, AfterViewInit {

  // find NumInputComponent
  //@ViewChild(NumInputComponent)
  @ViewChild('abc')
  numInput!: NumInputComponent

  @ViewChildren(NumInputComponent)
  numInputs!: QueryList<NumInputComponent>

  nums: number[] = [ 1, 2, 3, 4, 5 ]

  newNum = 0

  ngOnInit(): void {
    console.info('>>>> onInit: ', this.numInput)
  }

  ngAfterViewInit(): void {
    //console.info('>>>> afterViewInit ', this.numInput)
    //console.info('>>>> name ', this.numInput.instanceName)
    console.info('>>>> children ', this.numInputs)
    for (let c of this.numInputs)
      console.info('>>> num input: ', c.instanceName)
  }

  whenNewNumber($event: number) {
    console.info('>>> $event: ', $event)
    this.newNum = $event
    // changing the reference type in-situ, create a new reference
    //this.nums.unshift($event)
    //const newNums = []
    //for (let v of this.nums)
    //  newNums.push(v)
    //this.nums = newNums

    // ... spread operator
    // this.nums = [ $event, 1, 2, 3, 4, 5 ]
    this.nums = [ ...this.nums, $event ]
  }

  square() {
    console.info('>>> performing square')
    let currVal = this.numInput.value
    this.numInput.value = currVal * currVal
    // Manual submit
    this.numInput.process()
  }
  negate() {
    console.info('>>> performing negate')
    let currVal = this.numInput.value
    this.numInput.value = -currVal
    this.numInput.process()
  }
}
