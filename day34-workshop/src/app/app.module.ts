import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { SearchFormComponent } from './components/search-form.component';
import { ImageDisplayComponent } from './components/image-display.component';
import {ReactiveFormsModule} from '@angular/forms';
import {provideHttpClient} from '@angular/common/http';
import {GiphyService} from './giphy.service';

@NgModule({
  declarations: [ AppComponent, SearchFormComponent, ImageDisplayComponent ],
  imports: [ BrowserModule, ReactiveFormsModule ],
  providers: [ provideHttpClient(), GiphyService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
