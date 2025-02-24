import {HttpClient, HttpParams} from "@angular/common/http";
import {inject, Injectable} from "@angular/core";
import { SearchCriteria } from "./models";
import { firstValueFrom, map, Subject, tap } from "rxjs";

@Injectable()
export class GiphyService {

  // TODO: remove this
  private API_KEY = "set this"

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
        .set('api_key', this.API_KEY)

    return firstValueFrom<string[]>(
      this.http.get<any>('https://api.giphy.com/v1/gifs/search', { params }).pipe(
        // tap(result => {
        //   console.info('>>>> TAP-0: ', result)
        // }),
        map(result => result['data']),
        // tap(result => {
        //   console.info('>>>> TAP-1: ', result)
        // }),
        // data -> any[]
        map((data: any[]) => data.map((g: any) => g.images.fixed_height.url)),
        // fixed_height -> string[]
        tap(images => {
          this.searchResults.next(images)
        })
      )
    )
    // .then(result => {
    //   this.searchResults.next(result)
    //   return result
    // })
  }
}
