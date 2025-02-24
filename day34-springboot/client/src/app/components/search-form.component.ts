import { Component, inject, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SearchCriteria} from '../models';

@Component({
  selector: 'app-search-form',
  standalone: false,
  templateUrl: './search-form.component.html',
  styleUrl: './search-form.component.css'
})
export class SearchFormComponent implements OnInit {

  private fb = inject(FormBuilder)

  protected searchForm!: FormGroup
  protected resultsCount = 5

  ngOnInit(): void {
    this.searchForm = this.createSearchForm()
  }

  protected search() {
    const value: SearchCriteria = this.searchForm.value
    console.info('>>> value: ', value)
  }

  protected clear() {
    this.searchForm = this.createSearchForm()
  }

  limitUpdated($event: any) {
    this.resultsCount = parseInt($event.target.value)
  }

  private createSearchForm(): FormGroup {
    return this.fb.group({
      q: this.fb.control<string>('', [ Validators.required, Validators.minLength(1) ]),
      limit: this.fb.control<number>(5, [ Validators.min(1), Validators.max(25) ]),
      rating: this.fb.control<string>('pg', [ Validators.required ])
    })
  }

}
