package com.joyfe.daw.des.util;

import java.util.ArrayList;
import java.util.List;

public class Clase {
	
	private String nombre;
	private String nombreBBDD;
	private List<Columna> columnas = new ArrayList();
	
	public Clase() {
		Columna colPk = new Columna();
		colPk.setNombre("id");
		colPk.setTipo("Long");
		colPk.addPropiedad("@Id");
		colPk.addPropiedad("@GeneratedValue");
		columnas.add(colPk);
	}
	
	public String getNombrePlural() {
		return getNombreBBDD().substring(0,1).toUpperCase() + getNombreBBDD().substring(1);
	}
	
	public String getNombre() {
        return nombre;
    }
	
	public String getNameFile() {
		return this.getNombre() + ".java";
	}
    
	public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
	public String getNombreBBDD() {
        return nombreBBDD;
    }
    
	public void setNombreBBDD(String nombreBBDD) {
        this.nombreBBDD = nombreBBDD.replace("\n", "").replace("\t", "").trim();
    }
	
	public List<Columna> getColumnas() {
		return columnas;
	}
	
	public void setColumnas(List<Columna> columnas) {
		this.columnas = columnas;
	}
	
	public String toString() {
		String data = "";
		data += "\n\nimport jakarta.persistence.CascadeType;\r\n"
				+ "import jakarta.persistence.Entity;\r\n"
				+ "import jakarta.annotation.Nullable;\r\n"
				+ "import jakarta.persistence.FetchType;\r\n"
				+ "import jakarta.persistence.GeneratedValue;\r\n"
				+ "import jakarta.persistence.Id;\r\n"
				+ "import jakarta.persistence.JoinColumn;\r\n"
				+ "import jakarta.persistence.JoinTable;\r\n"
				+ "import jakarta.persistence.ManyToOne;\r\n"
				+ "import jakarta.persistence.ManyToMany;\r\n"
				+ "import jakarta.persistence.OneToMany;\r\n"
				+ "import jakarta.persistence.OneToOne;\r\n"
				+ "import jakarta.persistence.Column;\r\n"
				+ "import java.time.LocalDateTime;\r\n"
				+ "import lombok.AllArgsConstructor;\r\n"
				+ "import lombok.Getter;\r\n"
				+ "import lombok.NoArgsConstructor;\r\n"
				+ "import lombok.NonNull;\r\n"
				+ "import lombok.RequiredArgsConstructor;\r\n"
				+ "import java.util.List;\r\n"
				+ "import lombok.Setter;\r\n";
		data += "  \r\n@Entity(name = \"" + this.getNombreBBDD() + "\")\r\n"
				+ "@Getter\r\n"
				+ "@Setter\r\n"
				+ "@NoArgsConstructor\r\n"
				+ "@AllArgsConstructor\r\n"
				+ "@RequiredArgsConstructor\r\n";
		data += "public class " + this.getNombre() + " {\n\n";
		for (Columna col : columnas) {
			for (String propiedad : col.getPropiedades()) {
				data += "\t" + propiedad + "\n";
			}
			data += "\tprivate " + col.getTipo() + " " + col.getNombre() + ";\n\n"; 
		}
		return data + "\n\n}";
	}
}	