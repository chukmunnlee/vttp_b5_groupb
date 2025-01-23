import { Component, inject, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Observable, of} from 'rxjs';
import {Boardgame} from './models';
import {BGGService} from './bgg.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  private fb = inject(FormBuilder)
  private bggSvc = inject(BGGService)

  protected searchForm!: FormGroup
  protected result$: Observable<Boardgame | null> = of()

  ngOnInit(): void {
    this.clearForm()
  }

  search() {
    const q = this.searchForm.value.q
    this.result$ = this.bggSvc.search(q)
  }

  clearForm() {
    this.searchForm = this.createForm()
    this.result$ = of()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      q: this.fb.control('', [ Validators.required, Validators.minLength(2) ])
    })
  }
}
