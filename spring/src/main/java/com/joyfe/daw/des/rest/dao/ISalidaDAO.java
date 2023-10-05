package com.joyfe.daw.des.rest.dao;



import java.util.List;

import com.joyfe.daw.des.response.Salida;

public interface ISalidaDAO {

	public Salida getSalidaById(Long id);
	
	public List<Salida> getAllSalidas();
	
	public Salida addSalida(Salida salida);
	
	public Salida updateSalida(Long id, Salida salida);
	
	public Salida deleteSalida(Long id);
	
}