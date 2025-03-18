import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivateFn, Router, CanDeactivateFn, RouterStateSnapshot } from "@angular/router";
import { AppService } from "./app.service";
import { FormComponent } from "./components/form.component";

export const canProceedToForms: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  // boolean Promise<boolea>, Observable<boolean>
  // UrlTree Promise<UrlTree> Observable<UrlTree>
  const appSvc = inject(AppService)
  const router = inject(Router)

  if (!appSvc.checked)
    return router.parseUrl('/notice')

  return true
}

export const canLeaveForm: CanDeactivateFn<FormComponent> = (form: FormComponent,
    route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {

  // boolean Promise<boolea>, Observable<boolean>
  // UrlTree Promise<UrlTree> Observable<UrlTree>
  if (!form.prestine)
    return confirm('Form data is not saved.\nAre you sure you want to leave?')

  return true
}

