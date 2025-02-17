import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-image',
  standalone: false,
  templateUrl: './image.component.html',
  styleUrl: './image.component.css'
})
export class ImageComponent {

  @Input()
  imgNum = 0

}
