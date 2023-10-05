import { Patron } from "./Patron";

export class Salida {
    id?: number | null = null;
    fechaHoraSalida: Date | null = null;
    destino: string = "";
    patron: Patron|null = null;
    anulada: boolean = false;
	motivoAnulacion:String = "";
}