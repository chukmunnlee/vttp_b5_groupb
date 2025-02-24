import { Component, inject } from '@angular/core';
import { DogService } from './dog.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {

  private dogSvc = inject(DogService)

  protected dogImages: string[] = []
  private sub!: Subscription

  fetchDogImageAsPromise() {
    this.dogSvc.getDogsAsPromise()
      .then((result: string[]) => {
        console.info('>>> PROMISE result: ', result)
        this.dogImages = result
      })
      .catch(err => {
        console.error('>>> PROMISE error', err)
        alert(`ERROR: ${JSON.stringify(err)} `)
      })

  }
  fetchDogImages() {
    this.sub = this.dogSvc.getDogs()
      .subscribe({
        next: (result) => {
          console.info('>>> result: ', result)
          this.dogImages = result.message
        },
        error: (err) => {
          console.error('>>> ERROR', err)
        },
        complete: () => {
          console.info('>>>> COMPLETED !!!')
          this.sub.unsubscribe()
        }
      })

  }
}
