import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { PolarBearComponent } from './components/polar-bear.component';
import { DogComponent } from './components/dog.component';
import { FormComponent } from './components/form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NumbersComponent } from './components/numbers.component';
import { confirmRegistration, checkIfRegistrationIsSave } from './guards';

// Create application routes
const appRoutes: Routes = [
  { path: '', component: PolarBearComponent },
  { path: 'dog', component: DogComponent },
  { path: 'register', component: FormComponent,
    canActivate: [ confirmRegistration ],
    canDeactivate: [ checkIfRegistrationIsSave ]
  },
  { path: 'number/:num', component: NumbersComponent },
  // wild card must be the last route
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [ AppComponent, PolarBearComponent, DogComponent, FormComponent, NumbersComponent ],
  imports: [
    BrowserModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
