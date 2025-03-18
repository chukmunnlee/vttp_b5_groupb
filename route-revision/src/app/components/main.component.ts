import { Component, inject } from '@angular/core';
import {AppService} from '../app.service';

@Component({
  selector: 'app-main',
  standalone: false,
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

  private appSvc = inject(AppService)

  agreed($event: any) {
    console.info('>>>> checked: ', $event.target.checked)
    this.appSvc.checked = !!$event.target.checked
  }

}
