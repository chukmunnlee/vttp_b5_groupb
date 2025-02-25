import { inject, Injectable } from "@angular/core";
import { SearchCriteria, SearchResult } from "./models";
import { HttpClient, HttpParams } from "@angular/common/http";
import { firstValueFrom, Observable } from "rxjs";

@Injectable()
export class BGGService {

  private http = inject(HttpClient)

  searchAsObservable(criteria: SearchCriteria): Observable<SearchResult[]> {
    const params = new HttpParams()
        //.set('q', criteria.q.replaceAll(' ', '+'))
        .set('q', criteria.q)
        .set('count', criteria.count)
    return this.http.get<SearchResult[]>('/api/search', { params })
  }

  search(criteria: SearchCriteria): Promise<SearchResult[]> {
    return firstValueFrom(
      this.searchAsObservable(criteria)
    )
  }

}
