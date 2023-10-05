package com.joyfe.daw.des.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyfe.daw.des.response.Barco;
import com.joyfe.daw.des.rest.dao.BarcoDAO;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BarcoService {

	@Autowired
	private BarcoDAO barcoDAO;

	public Barco getBarcoById(Long id) {
	    return barcoDAO.getBarcoById(id);
	}

	public List<Barco> getAllBarcos() {
	    return barcoDAO.getAllBarcos();
	}

	public Barco addBarco(Barco barco) {
	    return barcoDAO.addBarco(barco);
	}

	public Barco updateBarco(Long id, Barco barco) {
	    return barcoDAO.updateBarco(id, barco);
	}

	public Barco deleteBarco(Long id) {
	    return barcoDAO.deleteBarco(id);
	}


}