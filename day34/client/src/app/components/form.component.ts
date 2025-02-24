import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { debounceTime, map, Subject, Subscription } from 'rxjs';
import { DogService } from '../dog.service';

@Component({
  selector: 'app-form',
  standalone: false,
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit, OnDestroy {

  private fb = inject(FormBuilder)
  private dogSvc = inject(DogService)

  protected form!: FormGroup
  private valueSub!: Subscription
  private statusSub!: Subscription

  count = 0
  images: string[] = []

  private counterSub = new Subject<number>()

  pushData() {
    this.count++
    this.counterSub.next(this.count)
  }


  ngOnInit(): void {
    this.form = this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.minLength(5) ]),
      address: this.fb.control<string>('', [ Validators.required, Validators.minLength(5) ])
    })

    this.statusSub = this.dogSvc.newDogSearch.subscribe(
      (images) => this.images = images
    )

    // this.statusSub = this.counterSub.subscribe(
    //   (data) => { console.info('>>>> received: ', data) }
    // )

    /*
    this.statusSub = this.form.statusChanges
      .pipe(
        debounceTime(2000),
        map(v => v == "VALID")
      )
      .subscribe(
        (changes) => {
          console.info('>>> changes: ', changes)
        }
      )

    this.valueSub = this.form.valueChanges.subscribe({
      next: (value) => {
        console.info('>>> value: ', value)
      },
      error: (err) => {
        console.error('>>>> error: ', err)
      },
      complete: () => {
        console.info('>>> completed')
      }
    })
      */
  }

  ngOnDestroy(): void {
      // Unsubscribe when the component is deleted
      this.valueSub.unsubscribe()
      this.statusSub.unsubscribe()
  }

}
