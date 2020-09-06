import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private routeService: Router) { }

  ngOnInit(): void {
  }

  goToNewUserPage() {
    this.routeService.navigate(['/new_user']);
  }

  goToUserDetailsPage(){
    this.routeService.navigate(['/user_details']);
  }


  goToNewInsuranceRequestPage(){
    this.routeService.navigate(['/new_insurance_request']);
  }
}
