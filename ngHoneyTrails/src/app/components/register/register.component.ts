import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    isSuccessful = false;
    isRegisterFailed =false;
    errorMessage = '';
    newUser : User = new User();


  constructor(
    private authSvc: AuthService,
    private router: Router

  ) { }

  register(user: User){
    this.authSvc.register(user).subscribe(
      user => {


//
        this.router.navigateByUrl('/user/' + user.id);
      },
      fail => {
        console.error(fail);
      }
    );
  }

  ngOnInit(): void {
  }



}
