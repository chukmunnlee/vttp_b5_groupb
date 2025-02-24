import { Component } from '@angular/core';

@Component({
  selector: 'app-image-display',
  standalone: false,
  templateUrl: './image-display.component.html',
  styleUrl: './image-display.component.css'
})
export class ImageDisplayComponent {

  images: string[] = []

}
