import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-numbers',
  standalone: false,
  templateUrl: './numbers.component.html',
  styleUrl: './numbers.component.css'
})
export class NumbersComponent implements OnInit {

  private router = inject(Router)
  private activatedRoute = inject(ActivatedRoute)

  numImg = ''
  num = ''

  ngOnInit(): void {
     // /number/:num
     this.num = this.activatedRoute.snapshot.params['num']
     this.numImg = `/numbers/number${this.num}.jpg`
  }

}
