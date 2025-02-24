import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { GiphyService } from '../giphy.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-image-display',
  standalone: false,
  templateUrl: './image-display.component.html',
  styleUrl: './image-display.component.css'
})
export class ImageDisplayComponent implements OnInit, OnDestroy {

  private giphySvc = inject(GiphyService)

  private sub!: Subscription

  images: string[] = []

  ngOnInit(): void {
    this.sub = this.giphySvc.searchResults.subscribe({
      // next
      next: (images) => this.images = images
    })

  }
  ngOnDestroy(): void {
    this.sub.unsubscribe()
  }

}
