import { Component, OnInit } from '@angular/core';
import { InsuranceService } from '../../services/insurance.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-insurance-request',
  templateUrl: './new-insurance-request.component.html',
  styleUrls: ['./new-insurance-request.component.scss']
})
export class NewInsuranceRequestComponent implements OnInit {

  constructor(private insuranceService: InsuranceService,
    private routeService: Router) { }

  applicant: any = {};

  ngOnInit(): void {
  }

  ageCalculate() {
    if (this.applicant.dateOfBirth) {
      const time = new Date(this.applicant.dateOfBirth);
      var timeDiff = Math.abs(Date.now() - time.getTime());
      this.applicant.age = Math.floor((timeDiff / (1000 * 3600 * 24)) / 365);
    }
  }

  raiseRequest(): void {
    this.insuranceService.startNewProcess(this.applicant).subscribe((data) => {
      alert(data.message);
      this.applicant = {};
    }, (error) => {
      alert(JSON.stringify(error));
    });
  }

  goToProcessPortalPage() {
    this.routeService.navigate(['/process_portal']);
  }

  goToHomePage() {
    this.routeService.navigate(['/']);
  }
}
