package com.joyfe.daw.des.rest.dao;



import java.util.List;

import com.joyfe.daw.des.response.Barco;

public interface IBarcoDAO {

	public Barco getBarcoById(Long id);
	
	public List<Barco> getAllBarcos();
	
	public Barco addBarco(Barco barco);
	
	public Barco updateBarco(Long id, Barco barco);
	
	public Barco deleteBarco(Long id);
	
}