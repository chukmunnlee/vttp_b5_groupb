import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NumListComponent } from './components/num-list.component';
import { NumInputComponent } from './components/num-input.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [ AppComponent, NumListComponent, NumInputComponent ],
  imports: [ BrowserModule, ReactiveFormsModule ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
