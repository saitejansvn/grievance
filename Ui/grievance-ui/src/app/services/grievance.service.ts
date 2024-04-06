import { Injectable } from '@angular/core';
import { GrievanceModel } from '../models/grievance-model';
import { Observable } from 'rxjs';
import { CustomResponse } from '../models/custom-response';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class GrievanceService {





  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    Authorization:`Bearer ${this.loginService.getTokenService()}`
  }

  private base_url: string = 'http://localhost:9092';

  constructor(private httpClient:HttpClient,private loginService:LoginService) { }
 
  submitGrievanceType(grievanceModel: GrievanceModel): Observable<CustomResponse> {
   
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };
    
    return this.httpClient.post(`${this.base_url}/grievance/submit_grievance`, grievanceModel) as Observable<CustomResponse>;
  }

  

}
