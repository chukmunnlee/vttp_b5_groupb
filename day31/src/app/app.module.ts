import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header.component';
import { TextInputComponent } from './components/text-input.component';

@NgModule({
  declarations: [ AppComponent, HeaderComponent, TextInputComponent ],
  imports: [ BrowserModule ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
