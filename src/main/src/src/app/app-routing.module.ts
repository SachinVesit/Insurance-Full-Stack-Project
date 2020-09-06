import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NewuserComponent } from './newuser/newuser.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { NewInsuranceRequestComponent } from './components/new-insurance-request/new-insurance-request.component';
import { ProcessPortalComponent } from './components/process-portal/process-portal.component';
import { UserInboxComponent } from './components/user-inbox/user-inbox.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'user_details', component: UserDetailsComponent},
  { path: 'new_user', component: NewuserComponent },
  { path: 'new_user/:id', component: NewuserComponent },
  { path: 'new_insurance_request', component: NewInsuranceRequestComponent },
  { path: 'process_portal', component: ProcessPortalComponent },
  { path: 'user_inbox', component: UserInboxComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
