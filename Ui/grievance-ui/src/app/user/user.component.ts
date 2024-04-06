import { Component, OnInit, ViewChild } from '@angular/core';
import { GrievanceModel } from '../models/grievance-model';
import { UserDetails } from '../models/user-details';
import { LoginService } from '../services/login.service';
import { GrievanceService } from '../services/grievance.service';
import { NgForm } from '@angular/forms';


/***
 * @author Saiteja Nimmagadda 
 */
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  @ViewChild('grievanceForm') grievanceForm: NgForm;

  userDetails: UserDetails = new UserDetails();

  grievanceModel: GrievanceModel = new GrievanceModel();

  ngOnInit(): void {
    const storedUserDetails = localStorage.getItem('CurrentUser');

    this.userDetails = JSON.parse(storedUserDetails);
  }

  constructor(public loginService: LoginService, private griveanceService: GrievanceService) { }


  grievanceTypes = [
    { label: 'Health', value: 'Health' },
    { label: 'Clean And Green', value: 'Clean And Green' },
    { label: 'Lockers', value: 'Lockers' },
    { label: 'Tickets', value: 'Tickets' },
    { label: 'Parking', value: 'Parking' },
    { label: 'Queue', value: 'Queue' },
    { label: 'Food', value: 'Food' }
  ];

  healthGrievanceSubTypes = [
    { label: 'First Aid', value: 'First Aid' },
    { label: 'Emergency', value: 'Emergency' },
  ]

  cleanAndGreenSubTypes = [
    { label: 'Sanitary', value: 'Sanitary' },
    { label: 'Not Enough Dustbins', value: 'Not Enough Dustbins' },
    { label: 'Plantion Issues', value: 'Plantion Issues' }
  ]

  lockersSubTypes = [
    { label: 'Mobile Locker', value: 'Mobile Locker' },
    { label: 'Baggage Locker', value: 'Baggage Locker' },
    { label: 'Shoes Locker', value: 'Shoes Locker' }
  ]

  tickets = [
    { label: 'Issuing Related', value: 'Issuing Related' },
    { label: 'Cost Issue', value: 'Cost Issue' },
    { label: 'Upi Wallet Realted', value: 'Upi Wallet' },
    { label: 'Credit/Debit Cards', value: 'Credit/Debit Cards' }

  ]

  onGrievanceTypeClear() {
    this.grievanceModel = new GrievanceModel();
  }

  submitGrievance() {
    this.griveanceService.submitGrievanceType(this.grievanceModel).subscribe((res) => {
      if (res.statusCode === 201) {
        this.loginService.successAlert("Grievance Created Successfully");
        
      } else {
        this.loginService.failureAlert("Grivenavce Creation Failed");
      }
    }).add(()=>{
      this.resetForm();
    });
  }

  resetForm(){
    this.grievanceForm.resetForm();

  }

}
