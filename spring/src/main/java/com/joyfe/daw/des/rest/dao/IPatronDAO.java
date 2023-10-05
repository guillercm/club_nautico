package com.joyfe.daw.des.rest.dao;



import java.util.List;

import com.joyfe.daw.des.response.Patron;

public interface IPatronDAO {

	public Patron getPatronById(Long id);

	public Patron getPatronByDni(String dniPatron);

	public Patron addPatron(Patron patron);

	public List<Patron> getAllPatrones();
	
}