import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { GroupHikeComponent } from './components/group-hike/group-hike.component';
import { HikePhotoComponent } from './components/hike-photo/hike-photo.component';
import { HikeReportComponent } from './components/hike-report/hike-report.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterComponent } from './components/register/register.component';
import { TrailComponent } from './components/trail/trail.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'about', component: AboutComponent },
  { path: 'trails', component: TrailComponent },
  { path: 'hikereports', component: HikeReportComponent },
  { path: 'hikereports/:id', component: HikeReportComponent },
  { path: 'grouphikes', component: GroupHikeComponent },
  { path: 'photos', component: HikePhotoComponent },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
