import { Component, OnInit } from '@angular/core';
import { login } from '../models/login';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { UserDetails } from '../models/user-details';
import { JwtResponse } from '../models/jwt-response';
import Swal from 'sweetalert2';
import { TYPE } from '../models/TYPES';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements  OnInit{
 jwtResponse:JwtResponse=new JwtResponse();
  ngOnInit(): void {
    
  }
  constructor(private loginService:LoginService,private router:Router){

  }
 
  loginForm:login=new login();
 
  submit(){
    this.loginService.login(this.loginForm).subscribe((res)=>{
   if(res.statusCode===200){
    this.jwtResponse=res.data;
    if(this.jwtResponse.valid){
       this.loginService.isLoggedIn=true;
       localStorage.setItem('CurrentUser', JSON.stringify(this.jwtResponse));
       if(this.jwtResponse.role==='user'){
        this.router.navigate(['/user']);
        this.loginService.successAlert("User LoggedIn SuccessFully");
       }else{     
        this.router.navigate(['/admin']);
        this.loginService.successAlert("Admin LoggedIn SuccessFully");
       }
    }else if(res.data==='User Not Found'){
     this.loginService.failureAlert(res.data);
    }else{
      this.loginService.failureAlert("Invalid Credentials")
    }
   }
    })

  }
}
