import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FormComponent } from './components/form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, provideHttpClient } from '@angular/common/http';
import { DogService } from './dog.service';

@NgModule({
  declarations: [ AppComponent, FormComponent ],
  imports: [ BrowserModule, ReactiveFormsModule  ],
  // Import HTTPClientModule
  // Deprecated
  //imports: [ BrowserModule, ReactiveFormsModule, HttpClientModule  ],
  // Declaring the services
  providers: [
    provideHttpClient(), DogService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
