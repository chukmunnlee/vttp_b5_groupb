import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-num-list',
  standalone: false,
  templateUrl: './num-list.component.html',
  styleUrl: './num-list.component.css'
})
export class NumListComponent implements OnInit, OnChanges {

  @Input()
  values: number[] = []

  @Input()
  latest: number = 0

  total: number = 0

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.info('changes: ', changes)
   this.total = changes['values']
      .currentValue.reduce((acc: number, v: number) => acc + v)
  }

}
