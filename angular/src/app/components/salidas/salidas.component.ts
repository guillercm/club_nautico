import { Component, Input, ViewChild } from '@angular/core';
import { CalendarEvent, CalendarMonthViewComponent, CalendarMonthViewDay } from 'angular-calendar';
import { Salida } from 'src/entitys/Salida';
import { MonthViewDay } from 'calendar-utils';
import { Subject } from 'rxjs';
import { Utilidades } from '../globals';

@Component({
  selector: 'app-salidas',
  templateUrl: './salidas.component.html',
  styleUrls: ['./salidas.component.css']
})
export class SalidasComponent {

  @Input() salidas: Salida[] | null = null;

  @ViewChild('calendar') calendar!: any;

  refreshSubject = new Subject<any>();

  myDate = new Date();

  dayHeaders = ['L', 'M', 'X', 'J', 'V', 'S', 'D'];

  monthsHeader = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

  viewDate = new Date(this.myDate.getTime());

  indexSalidaActual:number = 0;

  diaSelecionado:number = 0;

  calendarEvents:CalendarEvent[] = []

  lastDayClicked:any|null

  aguaConfiguracion = {
    maxSalidasPorDia: 4, 
    minPorcentajeAgua: 0,
    maxPorcentajeAgua: 90
  }

  constructor() {
    this.setAgua();
  }

  siguienteSalida = () => {
    if (!this.salidas) return;
    if (this.indexSalidaActual === this.salidas.length - 1) {
      this.indexSalidaActual = 0;
    } else {
      this.indexSalidaActual++;
    }
  }

  changeCapitan = (e:any) => {
    console.log(e)
  }

  getCapitanes = () => Utilidades.patrones;

  cambiarSalidas = (event: any) => {
    this.diaSelecionado = event.day.date.getDate();
    //event.day.backgroundColor = "red";
    if (this.lastDayClicked != null) {
      this.lastDayClicked.events[0].cssClass = null;
    }
    if (event.day.events.length) {
      event.day.events[0] = {cssClass: "diaSelecionado"};
    } else {
      event.day.events.push({cssClass: "diaSelecionado"});
    }
    this.lastDayClicked = event.day;
  }

  getSalidasDiaSeleccionado = (dia:number = 0) => {
    if (this.salidas == null) return [];
    if (dia === 0) {
      dia = this.diaSelecionado;
    }
    const salidas: Salida[] = [];
    for (let salida of this.salidas) {
      const date = new Date(salida?.fechaHoraSalida || "");
      if (date.getFullYear() === this.myDate.getFullYear() && date.getMonth() === this.myDate.getMonth() && date.getDate() === dia) {
        salidas.push(salida);
      }
    }
    return salidas;
  }

  nextYear = () => {
    this.myDate.setFullYear(this.myDate.getFullYear() + 1);
    this.resetDate();
  }

  prevYear = () => {
    this.myDate.setFullYear(this.myDate.getFullYear() - 1);
    this.resetDate();
  }

  nextMonth = () => {
    this.myDate.setMonth(this.myDate.getMonth() + 1);
    this.resetDate();
  }

  prevMonth = () => {
    this.myDate.setMonth(this.myDate.getMonth() - 1);
    this.resetDate();
  }

  resetDate = ():void => {
    this.viewDate = new Date(this.myDate.getTime());
    this.diaSelecionado = 0;
    this.setAgua();
  }

  setAgua = ():boolean => {
    setTimeout(() => {
      const elements:HTMLCollection = this.calendar.cdr._lView[0].getElementsByTagName("mwl-calendar-month-cell");
      for (let i = 0, contador = 0; i < elements.length; i++) {
        const divDia = elements[i];
        if (divDia.classList.contains("cal-out-month")) continue;
        contador++;
        const dia = divDia.getElementsByClassName("cal-day-number")[0];
        const divAgua = divDia.getElementsByClassName("cal-cell-top")[0];
        divAgua.classList.add("divAgua");
        const numeroSalidas: number = this.getSalidasDiaSeleccionado(contador).length;
        let porcentajeAgua = 0;

        if (numeroSalidas >= this.aguaConfiguracion.maxSalidasPorDia) {
          porcentajeAgua = this.aguaConfiguracion.maxPorcentajeAgua;
        } else {
          porcentajeAgua = (numeroSalidas / this.aguaConfiguracion.maxSalidasPorDia) * 100;
          if (porcentajeAgua < this.aguaConfiguracion.minPorcentajeAgua) {
            porcentajeAgua = this.aguaConfiguracion.minPorcentajeAgua;
          } else if (porcentajeAgua > this.aguaConfiguracion.maxPorcentajeAgua) {
            porcentajeAgua = this.aguaConfiguracion.maxPorcentajeAgua;
          }
        }
        divAgua.setAttribute("style", `--liquid: ${porcentajeAgua}%;`);
      }
    }, 200);
    return true;
  }


  crearSalida = () => {
    if (this.diaSelecionado === 0) return;
    const promtSalida = prompt("Indica el destino de esta salida");
    if (promtSalida === null) return;
    const salidaDestino = promtSalida.toString().trim();
    if (!salidaDestino.length) return;
    console.log(this.viewDate)
    const newSalida:Salida = {
      id:null,
      fechaHoraSalida: new Date(this.viewDate.getFullYear(), this.viewDate.getMonth(), this.diaSelecionado),
      destino: salidaDestino,
      patron: this.getCapitanes()[0],
      anulada: false,
      motivoAnulacion: ""
    };
    this.salidas?.push(newSalida);
    this.setAgua();
  }
  

  number = (numero:any):number => {
    return numero * 1
  }

}
