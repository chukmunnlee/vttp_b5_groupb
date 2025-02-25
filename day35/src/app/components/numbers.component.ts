import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-numbers',
  standalone: false,
  templateUrl: './numbers.component.html',
  styleUrl: './numbers.component.css'
})
export class NumbersComponent implements OnInit, OnDestroy {

  private router = inject(Router)
  private activatedRoute = inject(ActivatedRoute)
  private title = inject(Title)

  protected numImg = ''
  protected num = ''
  protected subParams!: Subscription
  protected subQuery!: Subscription

  ngOnInit(): void {
     // /number/:num
     //this.num = this.activatedRoute.snapshot.params['num']
     this.subParams = this.activatedRoute.params.subscribe(
      params => {
        console.info('>>> params: ', params)
        this.num = params['num']
        this.numImg = `/numbers/number${this.num}.jpg`
        console.info('>>> queryParams: ', this.activatedRoute.snapshot.queryParams['size'])
        this.title.setTitle(`Number: ${this.num}`)
      }
     )
    //  this.subQuery = this.activatedRoute.queryParams.subscribe(
    //   params => {
    //     console.info('>>> queryParams: ', params)
    //   }
    //  )
  }

  ngOnDestroy(): void {
      console.info('>>>> unsubscribing')
      this.subParams.unsubscribe()
      //this.subQuery.unsubscribe()
  }

}
