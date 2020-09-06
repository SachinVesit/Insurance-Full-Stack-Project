import { Component } from '@angular/core';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'InsuranceApprovalAssignment';

  constructor(private userService: UserService){}

  loadPage: boolean = false;

  /**
   * before starting application this methods asks for user authentication
   */
  ngOnInit(): void {
    this.userService.getUsers().subscribe((data:any) => {
      this.loadPage = true;
    }, (error) =>{
      alert("Please login with your credentials");
    });
  }
}
