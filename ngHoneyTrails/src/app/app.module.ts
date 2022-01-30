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
import { HomeComponent } from './components/home/home.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterComponent } from './components/register/register.component';
import { AboutComponent } from './components/about/about.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AddressComponent } from './models/address/address.component';

@NgModule({
  declarations: [
    AppComponent,
    TrailComponent,
    UserComponent,
    HikePhotoComponent,
    HikeReportComponent,
    HikeReportCommentComponent,
    HomeComponent,
    NavigationComponent,
    NotFoundComponent,
    RegisterComponent,
    AboutComponent,
    LoginComponent,
    LogoutComponent,
    AddressComponent
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
