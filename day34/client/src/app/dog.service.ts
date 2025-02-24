import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { firstValueFrom, map, Observable, Subject } from "rxjs";
import { DogResults } from "./models";

//@Injectable({ providedIn: 'root' }) // -> @Service
@Injectable() // -> @Service
export class DogService {

  // RestTemplate
  private http = inject(HttpClient)

  newDogSearch = new Subject<string[]>()

  getDogsAsPromise(count = 3): Promise<string[]> {
    // Convert Observable -> Promise
    // return firstValueFrom(
    //   // Observable
    //   this.http.get<DogResults>(`https://dog.ceo/api/breeds/image/random/${count}`).pipe(
    //     map(result => result.message)
    //   )
    // )
    // Promise
    return firstValueFrom(
      this.http.get<DogResults>(`https://dog.ceo/api/breeds/image/random/${count}`)
    ).then(result => {
      this.newDogSearch.next(result.message)
      return result.message
    })
    .catch(err => {
      return [ 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmWru8q17zpOzzzT1s475ZS_8fOL1GS0teSw&s' ]
    })
  }

  getDogs(count = 3): Observable<DogResults> {
    return this.http.get<DogResults>(`https://dog.ceo/api/breeds/image/random/${count}`)
  }

}
