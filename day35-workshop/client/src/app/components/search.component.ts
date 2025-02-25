import { Component, inject, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { BGGService } from '../bgg.service';
import { SearchCriteria, SearchResult } from '../models';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-search',
  standalone: false,
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {

  private fb = inject(FormBuilder)
  private bggSvc = inject(BGGService)

  protected form!: FormGroup
  //protected results: SearchResult[] = []
  // protected results$!: Promise<SearchResult[]>
  protected results$!: Observable<SearchResult[]>

  ngOnInit(): void {
    this.form = this.fb.group({
      q: this.fb.control<string>('', [ Validators.required ])
    })
  }

  search() {
    const q = this.form.value.q

    // result -> Promise | Observable
    this.results$ = this.bggSvc.searchAsObservable({ q, count: 10 } as SearchCriteria)

    // this.bggSvc.search({ q, count: 10 } as SearchCriteria)
    //   .then(results => {
    //     this.results = results
    //   })
  }

}
