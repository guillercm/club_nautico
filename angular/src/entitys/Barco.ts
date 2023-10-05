import { Salida } from "./Salida";

export class Barco {
  id?: number;
  num_matricula: string = "";
  nombre: string = "";
  num_amarre: string = "";
  cuota: number = 0;
  salidas: Salida[]= [];
}