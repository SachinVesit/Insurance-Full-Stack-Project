import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { UserService } from '../user.service';
import { HomeComponent } from '../home/home.component';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { PasswordDialogComponent } from '../password-dialog/password-dialog.component';

@Component({
  selector: 'app-newuser',
  templateUrl: './newuser.component.html',
  styleUrls: ['./newuser.component.css']
})
export class NewuserComponent implements OnInit {

  constructor(private userService: UserService,
    private routeService: Router, 
    private activatedRout: ActivatedRoute,
    public dialog: MatDialog) { }

    passwordStr: String;
    headerStr: String = "Add new user:";
    idToUpdate: String;
    showPassfield: boolean = true;
    enableCreate: boolean = true;
    errorUsername: boolean = false;
    errorEmail: boolean = false;
    errorEmpCode: boolean = false;
    user: any = {};

  ngOnInit(): void {
    this.idToUpdate = this.activatedRout.snapshot.params["id"];

    if(this.idToUpdate != null && this.idToUpdate != ""){
      this.showPassfield = false;
      this.enableCreate = false;
      this.headerStr = "Update User details:";
      this.userService.getUserById(this.idToUpdate).subscribe((data:any) => {
        this.user = data;
      }, (error) =>{
        alert(JSON.stringify(error));
      });
    }
  }

  /*
  * validating if username and password is null
  */
  validateUserDetails(): boolean{
    if((this.user.username == null || this.user.username == "")
      || (this.user.password == null || this.user.password == "")
      || (this.user.emailId == null || this.user.emailId == "")){
        return false;
      }
    return true;
  }


  /**
   *  this method nevigates to user details page
   */
  goToUserDetailsPage(){
    this.routeService.navigate(['/user_details']);
  }

  /**
   * this method nevigates to home page
   */
  goToHomePage() {
    this.routeService.navigate(['/']);
  }


  userRoles = [
    {name:'User', value:'USER', checked:false},
    {name:'Requestor', value:'REQUESTOR', checked:false},
    {name:'Scrutinizer level 1', value:'SCRUTINIZERLEVEL1', checked:false},
    {name:'Scrutinizer level 2', value:'SCRUTINIZERLEVEL2', checked:false},
    {name:'Scrutinizer level 3', value:'SCRUTINIZERLEVEL3', checked:false},
    {name:'Underwriter level 1', value:'UNDERWRITERLEVEL1', checked:false},
    {name:'Underwriter level 2', value:'UNDERWRITERLEVEL2', checked:false},
    {name:'Underwriter level 3', value:'UNDERWRITERLEVEL3', checked:false}
  ]

  //returning selected roles for an user
  get selectedOptions() {
    return this.userRoles
              .filter(opt => opt.checked)
              .map(opt => opt.value)
  }


  /*
  * calling service api to save new user
  */
  saveUser(){
    this.user.authorities = this.selectedOptions;
    if(this.validateUserDetails()){
        this.userService.addUser(this.user).subscribe((data) => {
          alert(data.message);
          this.routeService.navigate(['/user_details']);
        }, (error) =>{
          alert(error);
        });
    }
  }

  /**
   * validates if username is already exists in database
   */
  validateUsername(){
    if(this.validateUserDetails()){
      this.userService.getUserByUsername(this.user.username).subscribe((data) =>{
        if(data.result != null){
          this.errorUsername = true;
        }else{
          this.errorUsername = false;
          this.validateEmail();
        }
      });
    }
  }

  /**
   * validates if given email is already exists in database
   */
  validateEmail(){
    this.userService.getUserByEmail(this.user.emailId).subscribe((data) =>{
      if(data.result != null){
        this.errorEmail = true;
      }else{
        this.errorEmail = false;
        this.validateEmpCode();
      }
    });
  }


  /**
   * validates if given employee code is already exists in database
   */
  validateEmpCode(){
    this.userService.getUserByEmpCode(this.user.employeeCode).subscribe((data) =>{
      if(data.result != null){
        this.errorEmpCode = true;
      }else{
        this.errorEmpCode = false;
        this.saveUser();
      }
    });
  }


/**
 * opens a dialog to input password before updating user details
 */
  getPasswordToUpdate(){
    const dialogRef = this.dialog.open(PasswordDialogComponent, {
      width: '500px',
      data: {password: this.passwordStr}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.passwordStr = result;
      if(this.passwordStr!=null && this.passwordStr != ""){
        this.updateUserDetails();
      }
    });
  }


/**
 * updates user details
 */
  updateUserDetails(){
    this.user.authorities = this.selectedOptions;
    this.user.password = this.passwordStr;
      this.userService.updateUser(this.user).subscribe((data) => {
        if(data.result){
          alert("User details updated successfully");
          this.routeService.navigate(['/user_details']);
        }else{
          alert("Incorrect Password! try again");
          this.passwordStr = "";
        }
      }, (error) =>{
        alert(JSON.stringify(error));
      });
  }

}
