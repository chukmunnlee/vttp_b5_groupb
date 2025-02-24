import { Component, inject, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SearchCriteria} from '../models';
import { GiphyService } from '../giphy.service';

@Component({
  selector: 'app-search-form',
  standalone: false,
  templateUrl: './search-form.component.html',
  styleUrl: './search-form.component.css'
})
export class SearchFormComponent implements OnInit {

  private fb = inject(FormBuilder)
  private giphySvc = inject(GiphyService)

  protected searchForm!: FormGroup
  protected resultsCount = 5

  ngOnInit(): void {
    this.searchForm = this.createSearchForm()
  }

  protected search() {
    const criteria: SearchCriteria = this.searchForm.value
    console.info('>>> criteria: ', criteria)
    this.giphySvc.search(criteria)
        .then(result => {
          console.info('>>> search result: ', result)
        })
  }

  protected clear() {
    this.searchForm = this.createSearchForm()
    this.giphySvc.clearResults()
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
