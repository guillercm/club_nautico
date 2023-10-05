package com.joyfe.daw.des.util;

import java.util.ArrayList;
import java.util.List;

public class Columna {
	
	private String nombre;
	
	private String tipo;
	
	private boolean isList = false;
	
	private List<String> propiedades = new ArrayList();
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return isList() ? "List<" + tipo + ">" : tipo;
	}

	public void setTipo(String tipo) {
		tipo = tipo.trim();
		if (tipo.startsWith("List")) {
			this.tipo = tipo.replace("List<", "").replace(">", "").trim();
			this.setList(true);
		} else {
			this.tipo = tipo;
			this.setList(false);
		}
	}

	public boolean isList() {
		return isList;
	}

	public void setList(boolean isList) {
		this.isList = isList;
	}

	public List<String> getPropiedades() {
		return propiedades;
	}
	
	public void addPropiedad(String propiedad) {
		propiedad = propiedad.trim();
		if (propiedad.startsWith("@JoinTable")) {
			String[] values = propiedad.replace("@JoinTable(", "").replace(")", "").replace("\"", "").split(",");
			String name = values[0].trim();
			String joinColumn = values[1].trim();
			String inverseJoinColumns = values[2].trim();
			propiedad = "@JoinTable(name = \"" + name +"\",\r\n"
					+ "            joinColumns = @JoinColumn(name = \"" + joinColumn +"\"),\r\n"
					+ "            inverseJoinColumns = @JoinColumn(name = \"" + inverseJoinColumns +"\"))\r\n";
		}
		this.propiedades.add(propiedad);
	}

	public void setPropiedades(String[] propiedades) {
		for (String propiedad : propiedades) {
			this.addPropiedad(propiedad);
		}
	}
	
	
	
}
