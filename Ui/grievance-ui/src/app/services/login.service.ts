import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDetails } from '../models/user-details';
import { login } from '../models/login';
import { CustomResponse } from '../models/custom-response';
import Swal from 'sweetalert2';
import { TYPE } from '../models/TYPES';
import { Router } from '@angular/router';
import { GrievanceModel } from '../models/grievance-model';
@Injectable({
  providedIn: 'root'
}
)

export class LoginService {

  isLoggedIn!: boolean;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  private base_url: string = 'http://localhost:9092';


  constructor(private httpClient: HttpClient, private router: Router) { }

  registerUser(UserDetails: UserDetails): Observable<CustomResponse> {
    return this.httpClient.post(
      `${this.base_url}/authenticate/register`, UserDetails) as Observable<CustomResponse>;

  }

  login(loginform: login): Observable<CustomResponse> {
    return this.httpClient.post(`${this.base_url}/authenticate/get_token`, loginform) as Observable<CustomResponse>;
  }

 


  checkUserNameExists(userName: string): Observable<CustomResponse> {
    const params = new HttpParams().set('userName', userName);
    const options = {
      params: params,
    };
    return this.httpClient.get<CustomResponse>
    (`${this.base_url}/authenticate/check_user_email_exists`, options);
  }

  logout() {
    this.successAlert('User Logged Out Successfully');
    localStorage.removeItem('CurrentUser');
    this.router.navigate(['/login']);
  }

  successAlert(title: string) {
    Swal.fire({
      toast: true,
      position: 'bottom-end',
      showConfirmButton: false,
      icon: TYPE.SUCCESS,
      timer: 3000,
      title: title,
    });
  }

  failureAlert(title: string) {
    Swal.fire({
      toast: true,
      position: 'bottom-end',
      showConfirmButton: false,
      icon: TYPE.ERROR,
      timer: 3000,
      title: title,
    });
  }

  getTokenService():string{
    const currentUser = JSON.parse(localStorage.getItem('CurrentUser'));
    const jwtToken = currentUser.jwttoken;
   return jwtToken;
  }
}
