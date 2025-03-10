import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {provideHttpClient} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';

import { AppComponent } from './app.component';
import { SearchComponent } from './components/search.component';
import { DetailsComponent } from './components/details.component';
import { BGGService } from './bgg.service';

const appRoutes: Routes = [
  { path: '', component: SearchComponent },
  { path: 'game/:gid', component: DetailsComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent, SearchComponent, DetailsComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [ provideHttpClient(), BGGService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
