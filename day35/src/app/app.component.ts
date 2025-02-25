import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {

  private router = inject(Router)

  private currNum = '5'

  numberChanged($event: any) {
    this.currNum = $event.target.value
    this.router.navigate(['/number', this.currNum ], { queryParams: { size: 'small' }})
    //console.info('>>> currNum: ', this.currNum)
  }
  showNumber() {
    this.router.navigate(['/number', this.currNum ])
  }

  showForm() {
    this.router.navigate(['/register'])

  }
}
