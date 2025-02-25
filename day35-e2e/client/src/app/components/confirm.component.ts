import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-confirm',
  standalone: false,
  templateUrl: './confirm.component.html',
  styleUrl: './confirm.component.css'
})
export class ConfirmComponent implements OnInit {

  private router = inject(Router)
  private activatedRoute = inject(ActivatedRoute)

  protected regId = ''

  ngOnInit(): void {
    this.regId = this.activatedRoute.snapshot.params['regId']
  }

}
