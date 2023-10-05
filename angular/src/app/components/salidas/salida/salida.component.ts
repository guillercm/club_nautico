import { Component, Input, OnInit } from '@angular/core';
import { Salida } from 'src/entitys/Salida';
import { Utilidades } from '../../globals';
import { Patron } from 'src/entitys/Patron';

@Component({
  selector: 'app-salida',
  templateUrl: './salida.component.html',
  styleUrls: ['../salidas.component.css', './salida.component.css']
})
export class SalidaComponent  implements OnInit {

  @Input() salida:Salida | null = null;

  selectedPatronId: number = 1;

  getCapitanes = () => Utilidades.patrones;

  time:string = "";

  constructor() {
    
  }

  getCapitan = ():Patron => {
    return Utilidades.getPatronById(this.selectedPatronId);
  }

  onChange = () => {
    console.log(this.salida)
    console.log('selectedPatronId', this.selectedPatronId);
    this.selectedPatronId = this.selectedPatronId * 1;
    if (this.salida === null) return;
    this.salida.patron = this.getCapitan();
  }

  ngOnInit() {
    if (this.salida === null || this.salida.patron === null) return;
    this.selectedPatronId = this.salida.patron.id || 1;
    if (this.salida.fechaHoraSalida === null) return;
    this.time = new Date(this.salida.fechaHoraSalida).toLocaleTimeString('en-US', { hour12: false });
  }

  anularSalida = () => {
    if (this.salida === null || this.salida.patron === null) return;
    if (this.salida.anulada) return;
    const promtMotivo = prompt("¿Cuál es el motivo de la anulación de la salida?");
    if (promtMotivo === null) return;
    const motivo = promtMotivo.toString().trim();
    if (motivo.length) {
      this.salida.anulada = true;
      this.salida.motivoAnulacion = motivo;
    }
  }

  onTimeChange(event: Event) {
    if (this.salida === null || this.salida.fechaHoraSalida === null) return;
    const target = event.target as HTMLInputElement;
    this.salida.fechaHoraSalida = new Date(this.salida.fechaHoraSalida);
    this.salida.fechaHoraSalida.setHours(parseInt(target.value.slice(0,2)));
    this.salida.fechaHoraSalida.setMinutes(parseInt(target.value.slice(3)));
  }
  
  

}
