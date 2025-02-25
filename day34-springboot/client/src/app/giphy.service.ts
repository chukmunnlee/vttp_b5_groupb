import {HttpClient, HttpParams} from "@angular/common/http";
import {inject, Injectable} from "@angular/core";
import { SearchCriteria } from "./models";
import { firstValueFrom, map, Subject, tap } from "rxjs";

@Injectable()
export class GiphyService {

  private http = inject(HttpClient)

  searchResults = new Subject<string[]>()

  clearResults() {
    this.searchResults.next([])
  }

  search(criteria: SearchCriteria): Promise<string[]> {
    const params = new HttpParams()
        // spaces => + eg 'sleeping babies' => 'sleeping+babies'
        .set('q', criteria.q.replace(' ', '+'))
        .set('limit', criteria.limit)
        .set('rating', criteria.rating)

    return firstValueFrom<string[]>(
      this.http.get<string[]>('http://localhost:8080/api/search', { params })
    )
    .then(result => {
       this.searchResults.next(result)
       return result
    })
  }
}
