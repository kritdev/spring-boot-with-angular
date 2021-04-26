import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxWebstorageModule } from 'ngx-webstorage';

import { AppComponent } from './app.component';
import { LoginComponent } from './security/login/login.component';
import { AuthInterceptor } from './security/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from './security/interceptor/auth-expired.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule, FormsModule, ReactiveFormsModule,
    NgxWebstorageModule.forRoot()
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
