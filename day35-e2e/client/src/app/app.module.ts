import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register.component';
import { ConfirmComponent } from './components/confirm.component';
import {ReactiveFormsModule} from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

const appRoutes: Routes = [
  { path: '', component: RegisterComponent },
  { path: 'confirm/:regId', component: ConfirmComponent },
]

@NgModule({
  declarations: [
    AppComponent, RegisterComponent, ConfirmComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ provideHttpClient() ],
  bootstrap: [AppComponent]
})
export class AppModule { }
