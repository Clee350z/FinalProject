import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userLogin : User = new User();

  constructor(
    private authSvc: AuthService,
    private router: Router
  ) { }

  login(user: User){
    this.authSvc.login(user.username, user.password).subscribe(
      success => {
        this.router.navigateByUrl('/home');
      },
      fail => {
        console.error(fail);
      }
    );
  }

  ngOnInit(): void {
  }

}
