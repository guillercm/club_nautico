import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { BarcoComponent } from './components/barco/barco.component';
import { SociosComponent } from './components/socios/socios.component';
import { SalidasComponent } from './components/salidas/salidas.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { FormsModule } from '@angular/forms';
import { SalidaComponent } from './components/salidas/salida/salida.component';
import { CommonModule } from '@angular/common';
@NgModule({
  declarations: [
    AppComponent,
    BarcoComponent,
    SociosComponent,
    SalidasComponent,
    SalidaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
