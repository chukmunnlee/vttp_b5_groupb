import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivateFn, CanDeactivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { FormComponent } from "./components/form.component";

export const confirmRegistration: CanActivateFn =
  (activatedRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    const router = inject(Router)
    // true or false
    // boolean, Promise<boolean>, Observable<boolean>
    if (confirm('Are you sure you want to register?'))
      return true


    // UrlTree, Promise<UrlTree>, Observable<UrlTree>
    return router.parseUrl('/number/8')
  }

  export const checkIfRegistrationIsSave: CanDeactivateFn<FormComponent> =
    (form: FormComponent, activatedRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
      if (!form.shouldSave())
        return true

      // true -> navigate away
      // false -> stay where we are
      return confirm("You have not saved your form. All data will be lost if you navigate away from this page. Proceed?")

    }

