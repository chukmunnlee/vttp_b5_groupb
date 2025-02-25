import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-details',
  standalone: false,
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent implements OnInit {

  private activatedRoute = inject(ActivatedRoute)

  gid: number = 0

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      params => {
        this.gid = parseInt(params['gid'])
      }
    )
  }

}
