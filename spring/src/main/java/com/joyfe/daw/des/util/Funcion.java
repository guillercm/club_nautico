package com.joyfe.daw.des.util;

import java.util.List;

import com.joyfe.daw.des.response.Salida;

public class Funcion {

	enum tipos {
		interfaz,
		dao,
		service,
		controller
	}
	
	private String nombre;
	
	private String nombreFuncion;
	
	private List<String> parametros;
	
	private String nombrePlural;
	
	private String valorRetornado;
	
	private String code;
	
	
	public String getCodeInterface() {
		return getNombreFuncion() + ";";
	}
	
	public String getCodeDao() {
		return "@Override\r\n"
				+ getNombreFuncion() + " {\r\n"
				+ code + "\r\n"
				+ "	}	";
	}
	
	public String getCodeService(String var_dao) {
		return getNombreFuncion() + " {\r\n"
				+ (valorRetornado == "void" ? "" : "return ") + "salidaDAO.getSalidaById(id);\r\n"
				+ "    }";
	}
	
	public String getCodeController() {
		return "";
	}
	
	private String getNombreFuncion() {
		return "public " + valorRetornado + " " + nombreFuncion + "(" + getParametrosConTipos() + ")";
	}
	
	private String getParametrosConTipos() {
		String res = "";
		for (int i = 0; i < parametros.size(); i++) {
			res += parametros.get(i) + " " + parametros.get(i + 1) + ", ";
		}
		return res.substring(0, res.length()-2);
	}
	
	private String getParametros() {
		String res = "";
		for (int i = 0; i < parametros.size(); i++) {
			res += parametros.get(i + 1) + ", ";
		}
		return res.substring(0, res.length()-2);
	}

	public String getNombreClase() {
		return nombre.substring(0,1).toUpperCase() + nombre.substring(1);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toLowerCase();
	}

	public String getNombrePlural() {
		return nombrePlural;
	}

	public void setNombrePlural(String nombrePlural) {
		this.nombrePlural = nombrePlural.toLowerCase();
	}

	public String getValueReturning() {
		return valorRetornado;
	}

	public void setValueReturning(String valorRetornado) {
		this.valorRetornado = valorRetornado;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
