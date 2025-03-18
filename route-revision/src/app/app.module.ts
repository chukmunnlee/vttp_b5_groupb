import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { FormComponent } from './components/form.component';
import { NoticeComponent } from './components/notice.component';
import {RouterModule, Routes} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {AppService} from './app.service';
import { canLeaveForm, canProceedToForms } from './guards';

const appRoute: Routes = [
  { path: '', component: MainComponent },
  { path: 'form', component: FormComponent,
      canActivate: [ canProceedToForms ], canDeactivate: [ canLeaveForm ]
  },
  { path: 'notice', component: NoticeComponent },
]

@NgModule({
  declarations: [
    AppComponent, MainComponent, FormComponent, NoticeComponent
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoute)
        , ReactiveFormsModule
  ],
  providers: [ AppService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
