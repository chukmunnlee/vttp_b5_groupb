import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ImageComponent } from './components/image.component';
import { PaginationComponent } from './components/pagination.component';

@NgModule({
  declarations: [ AppComponent, ImageComponent, PaginationComponent ],
  imports: [ BrowserModule ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
