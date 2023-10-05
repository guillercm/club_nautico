import { Component } from '@angular/core';
import { Socio } from 'src/entitys/Socio';
import { Observable } from 'rxjs/internal/Observable';
import { SocioService } from 'src/app/services/socio.service';
import { PatronesService } from 'src/app/services/patrones.service';
import { Utilidades } from '../globals';

@Component({
  selector: 'app-socios',
  templateUrl: './socios.component.html',
  styleUrls: ['./socios.component.css']
})
export class SociosComponent {


  constructor(private socioService:SocioService, private patronesServices:PatronesService) {
    
  }

  btnActivado:boolean = true;

  getSocios = () => Utilidades.socios;

  ngOnInit() {
    this.socioService.getSocios().subscribe((data: any) => {
      Utilidades.socios = data;
      console.log(Utilidades.socios);
    });
    this.patronesServices.getPatrones().subscribe((data: any) => {
      Utilidades.patrones = data;
      console.log(Utilidades.patrones)
    });
  }

  actualizarSocio = (socio:Socio) => {
    this.btnActivado = false;
    this.socioService.updateSocio(socio.id, socio).subscribe((data: any) => {
      this.btnActivado = true;
      alert("Socio actualizado con éxito")
    });
  }
}
