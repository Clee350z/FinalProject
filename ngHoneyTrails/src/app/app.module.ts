import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from './services/auth.service';
import { TrailComponent } from './components/trail/trail.component';
import { UserComponent } from './components/user/user.component';
import { HikePhotoComponent } from './components/hike-photo/hike-photo.component';
import { HikeReportComponent } from './components/hike-report/hike-report.component';
import { HikeReportCommentComponent } from './components/hike-report-comment/hike-report-comment.component';

@NgModule({
  declarations: [
    AppComponent,
    TrailComponent,
    UserComponent,
    HikePhotoComponent,
    HikeReportComponent,
    HikeReportCommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
