import { Barco } from "./Barco";

export class Socio {
    public id: number = 0;
    public nombre: string = "";
    public apellido: string = "";
    public dni: string = "";
    public barcos: Barco[] = [];
}