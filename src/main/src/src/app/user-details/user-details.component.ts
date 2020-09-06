import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../models/User';
import { Router } from '@angular/router';
import { UserRole } from '../models/UserRole';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  constructor(private userService: UserService,
    private routeService: Router) {
    this.columns = [
      { name: 'Username', prop: 'username' },
      { name: 'Email', prop: 'emailId' },
      { name: 'User Roles', prop: 'authorities("authority")' },
      { name: 'Department', prop: 'department' }
    ];
  }

  users: Array<any> = [];

  public columns: any;

  passwordStr: String;

  ngOnInit(): void {
    this.refreshUsers();
  }


  goToNewUserPage() {
    this.routeService.navigate(['/new_user']);
  }

  goToHomePage() {
    this.routeService.navigate(['/']);
  }


  /*
  * gets all the user details from database
  */
  refreshUsers() {
    this.userService.getUsers().subscribe((data) => {
      this.users = data;
    }, (error) => {
      alert(JSON.stringify(error));
    });
  }


  /**
   * 
   * @param data 
   * data holds an user id an this methods deletes user details from database for given user id
   */
  deleteUser(data: any) {
    if (confirm("Are you sure to delete")) {
      this.userService.deleteUser(data.id).subscribe((data) => {
        alert(data.message);
        this.refreshUsers();
      });
    }
  }

  updatUser(id: String) {
    this.routeService.navigate(['/new_user', id]);
  }

}
