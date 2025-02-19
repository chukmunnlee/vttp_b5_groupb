import { Component, OnInit } from '@angular/core';
import { AnimationOptions } from 'ngx-lottie';
import { LOTTIE } from './constants';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  // animation index
  index = 0

  // Lottie file options
  options: AnimationOptions = {
    path: LOTTIE[this.index]
  }

  width = '80vw'

  share = false

  ngOnInit(): void {
    const v = localStorage.getItem('idx')

    this.index = parseInt(v || '0')
    console.info('>>> this.index: ', this.index)
    this.options = { path: LOTTIE[this.index] }

    console.info('>> share: ', navigator.share)
    this.share = !!navigator.share
    console.info('>> share: ', this.share)

  }

  shareContent() {
    navigator.share({
      title: 'Fun Angular App',
      text: 'Checkout my first PWA',
      url: window.location.origin
    })
  }

  nextAnimation() {
    this.index = (this.index + 1) % LOTTIE.length
    // recreate options for change detection to work
    this.options = { path: LOTTIE[this.index] }

    // save the index to localStorage
    localStorage.setItem('idx', `${this.index}`)
    localStorage.setItem('updated', (new Date()).toISOString())
  }
}
