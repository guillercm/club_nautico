import { Component, Input } from '@angular/core';
import { Barco } from 'src/entitys/Barco';

@Component({
  selector: 'app-barco',
  templateUrl: './barco.component.html',
  styleUrls: ['./barco.component.css']
})
export class BarcoComponent {
  @Input() barcos: Barco[] | null = null;
}