import { Component, OnInit } from '@angular/core';
import { UserDetails } from '../models/user-details';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

 isUserNameExists!:boolean;

  userDetails: UserDetails = new UserDetails();

  Roles = [  
    { label: 'Admin', value: 'admin' },
    { label: 'User', value: 'user' }
  ];
  ngOnInit(): void {
  }
  constructor(private loginService: LoginService, private router: Router) {}

  register() {
    this.loginService.registerUser(this.userDetails).subscribe((res) => {
      if (res.statusCode===200) {
       this.loginService.successAlert(res.data);
        this.router.navigate(['/login']);
      } else {
        this.loginService.failureAlert(res.data);
        this.router.navigate(['/login']);
      }
    })
  }
  checkUserNameExists(userName:any){
if(userName!==null){
  this.loginService.checkUserNameExists(userName).subscribe((res)=>{
   if(res.statusCode===302){
  this.isUserNameExists=true;
  }else{
    this.isUserNameExists=false;  
  }
});

}
  }
}