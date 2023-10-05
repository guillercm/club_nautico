package com.joyfe.daw.des.rest.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyfe.daw.des.response.Barco;
import com.joyfe.daw.des.response.Patron;
import com.joyfe.daw.des.util.Utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PatronDAO implements IPatronDAO {

	@PersistenceContext
	public EntityManager em;
	
	@Override
	public Patron getPatronById(Long id) {
		return em.find(Patron.class, id);
	}

	@Override
	public Patron getPatronByDni(String dniPatron) {
		Utilities.setEntityManager(em);
		return Utilities.find(Patron.class, true, "dniPatron", dniPatron);
	}

	@Override
	public Patron addPatron(Patron patron) {
	    Patron patron_bbdd = getPatronByDni(patron.getDniPatron());
	    if (patron_bbdd != null) {
	        patron_bbdd.setNombrePatron(patron.getNombrePatron());
	        patron_bbdd.setApellidosPatron(patron.getApellidosPatron());
	        em.merge(patron_bbdd); // actualizar entidad existente
	        return patron_bbdd;
	    } else {
	        em.persist(patron); // crear nueva entidad
	        return patron;
	    }
	}

	@Override
	public List<Patron> getAllPatrones() {
		TypedQuery<Patron> query = em.createQuery("SELECT p FROM patrones p", Patron.class);
		return query.getResultList();
	}	
}