import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import {AuthServerProvider} from "app/core/auth/auth-jwt.service";
import {LoginService} from "app/core/login/login.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  displayName: String = "";

  authenticationError = false;

  autoLogin = "no";

  constructor(private accountService: AccountService, private loginModalService: LoginModalService,
              private authServerProvider: AuthServerProvider,
              private loginService: LoginService, private router: Router,
              private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    if(!this.accountService.isAuthenticated()) {
      this.activatedRoute.queryParams.subscribe(params => {
        const userName = params['userName'];
        this.autoLogin = params['auto_login'];
        if(userName != null) {
          this.displayName = userName;
        }
      });
      // auto login
      if(this.autoLogin === "true") {
        this.loginService
          .login({
            username: "user",
            password: "Password1#",
            rememberMe: false,
          })
          .subscribe(
            () => {
              if (
                this.router.url === '/account/register' ||
                this.router.url.startsWith('/account/activate') ||
                this.router.url.startsWith('/account/reset/')
              ) {
                this.router.navigate(['']);
              }
            },
            () => {
              this.authenticationError = true
            }
          );
      }

    }
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
}
