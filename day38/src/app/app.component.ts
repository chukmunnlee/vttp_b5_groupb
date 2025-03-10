import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  @Input()
  title = 'day38';

  ngOnInit(): void {
      console.info('>>> ', this.title)
      // @ts-ignore
      console.info('>>> ', window['mydata'])
      // @ts-ignore
      this.title = window['mydata']
  }
}
