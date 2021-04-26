import { Component } from '@angular/core';
import { DataService } from './data.service';
import { AccountService } from './security/auth/account.service';
import { LoginService } from './security/login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  data = null;
  currentLogin = null;

  constructor(private dataService: DataService, 
    private accountService: AccountService,
    private loginService:LoginService) {
  }

  ngOnInit(): void {
    // try to log in automatically
    this.accountService.identity().subscribe();
    
    this.getData();
  }

  getData() {
    this.data = null;
    
    this.dataService
      .retrieveData()
      .subscribe(
        result => { this.data = result as any[]; },
        err => { alert(err); }
      );
  }

  isAuthenticated(): boolean {
    if (this.accountService.isAuthenticated()) {
      this.getLoginName();
      return true;
    } 

    return false;
  }

  getLoginName() {
    this.accountService.identity()
      .subscribe(
        account => { this.currentLogin = account.login; }
      )
  }

  logout() {
    this.loginService.logout();
  }
}
