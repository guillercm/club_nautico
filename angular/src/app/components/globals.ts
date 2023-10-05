import { Patron } from "src/entitys/Patron";
import { Socio } from "src/entitys/Socio";

export class Utilidades {



    public static patrones:Patron[] = []
    
    public static socios:Socio[] = []
    
    public static getPatronById(id: number):Patron {
        for (const p of Utilidades.patrones) {
            if (p.id === id) return p;
        }
        return Utilidades.patrones[0];
    }
}