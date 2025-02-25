import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { ConfirmRegistration, RegistrationDetails } from "./models";
import { firstValueFrom } from "rxjs";

@Injectable({ providedIn: 'root' })
export class RegistrationService {

  // HTTP client
  private http = inject(HttpClient)

  register(details: RegistrationDetails): Promise<ConfirmRegistration> {
    return firstValueFrom(
      // /register -> locahost:4200/register
      // during development -> localhost:8080/register
      this.http.post<ConfirmRegistration>('/api/register', details)
    )
  }

  // /register

}
