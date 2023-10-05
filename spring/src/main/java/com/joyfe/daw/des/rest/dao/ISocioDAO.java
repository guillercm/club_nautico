package com.joyfe.daw.des.rest.dao;



import java.util.List;


import com.joyfe.daw.des.response.Socio;

public interface ISocioDAO{

	public Socio getSocioById(Long id);

	public List<Socio> getAllSocios();

	public Socio addSocio(Socio socio);

	public Socio updateSocio(Long id, Socio socio);

	public Socio deleteSocio(Long id);
	
}