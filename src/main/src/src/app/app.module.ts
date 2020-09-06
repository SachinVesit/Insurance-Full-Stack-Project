import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { NewuserComponent } from './newuser/newuser.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PasswordDialogComponent } from './password-dialog/password-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { UserDetailsComponent } from './user-details/user-details.component';
import { MatIconModule } from '@angular/material/icon'
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { IonicModule } from '@ionic/angular';
import { NewInsuranceRequestComponent } from './components/new-insurance-request/new-insurance-request.component';
import { ProcessPortalComponent } from './components/process-portal/process-portal.component';
import { UserInboxComponent } from './components/user-inbox/user-inbox.component';
import { CommentBoxComponent } from './components/comment-box/comment-box.component';




@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NewuserComponent,
    PasswordDialogComponent,
    UserDetailsComponent,
    NewInsuranceRequestComponent,
    ProcessPortalComponent,
    UserInboxComponent,
    CommentBoxComponent
  ],
  entryComponents: [PasswordDialogComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatIconModule,
    NgxDatatableModule,
    IonicModule,
    


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
