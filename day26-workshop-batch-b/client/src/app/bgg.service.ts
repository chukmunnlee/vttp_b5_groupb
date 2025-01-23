import {inject, Injectable} from "@angular/core";
import {catchError, Observable, of, throwError} from "rxjs";
import {Boardgame} from "./models";
import {HttpClient} from "@angular/common/http";

const test: Boardgame = {
  "gid" : 12493,
  "name" : "Twilight Imperium (Third Edition)",
  "ranking" : 54,
  "url" : "https://www.boardgamegeek.com/boardgame/12493/twilight-imperium-third-edition",
  "image" : "https://cf.geekdo-images.com/micro/img/DX1nX1JYHVRQXhoPMaDgaHfezsg=/fit-in/64x64/pic4128153.jpg",
  "comments": [
    {
      "user" : "Furunkulus",
      "rating" : 8,
      "text" : "Great theme, and a very fun game.",
    }
  ]
}


@Injectable()
export class BGGService {

  private http = inject(HttpClient)

  search(q: string): Observable<Boardgame | null> {
    //return of(test)
    return this.http.get<Boardgame|null>('/api/search', { params: { q } })
      .pipe(
        catchError(err => {
          var msg = err.message
          if (err.error)
            msg = err.error.message
          alert(`ERROR:\n${msg}`)
          return throwError(() => err)
        })
      )

  }
}
