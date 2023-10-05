package com.joyfe.daw.des.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyfe.daw.des.response.Barco;
import com.joyfe.daw.des.response.Patron;
import com.joyfe.daw.des.response.Salida;
import com.joyfe.daw.des.response.Socio;
import com.joyfe.daw.des.rest.dao.BarcoDAO;
import com.joyfe.daw.des.rest.dao.ISalidaDAO;
import com.joyfe.daw.des.rest.dao.PatronDAO;
import com.joyfe.daw.des.rest.dao.SalidaDAO;
import com.joyfe.daw.des.rest.dao.SocioDAO;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SocioService {

	@Autowired
	private SocioDAO socioDAO;
	
	@Autowired
	private BarcoDAO barcoDAO;
	
	@Autowired
	private PatronDAO patronDAO;
	
	@Autowired
	private SalidaDAO salidaDAO;

	public Socio getSocioById(Long id) {
	    return socioDAO.getSocioById(id);
	}

	public List<Socio> getAllSocios() {
	    return socioDAO.getAllSocios();
	}

	public Socio addSocio(Socio socio) {
		for (Barco barco : socio.getBarcos()) {
			for (Salida salida : barco.getSalidas()) {
				String dniPatron = salida.getPatron().getDniPatron();
				Patron patron = patronDAO.getPatronByDni(dniPatron);
				if (patron == null) return null;
				salida.setPatron(patron);
			}
		}
	    return socioDAO.addSocio(socio);
	}

	public Socio updateSocio(Long id, Socio socio) {
		/*for (Barco barco : socio.getBarcos()) {
			int contador = 0;
			for (Salida salida : barco.getSalidas()) {
				if (salida.getId() == null) {
					Salida salidaAux = new Salida();
					salidaAux.setAnulada(salida.getAnulada());
					salidaAux.setDestino(salida.getDestino());
					salidaAux.setMotivoAnulacion(salida.getMotivoAnulacion());
					salidaAux.setFechaHoraSalida(salida.getFechaHoraSalida());
					salidaAux.setPatron(salida.getPatron());
					barco.getSalidas().set(contador, salidaAux);
				}
				contador++;
			}
		}*/
	    return socioDAO.updateSocio(id, socio);
	}

	public Socio deleteSocio(Long id) {
	    return socioDAO.deleteSocio(id);
	}

}