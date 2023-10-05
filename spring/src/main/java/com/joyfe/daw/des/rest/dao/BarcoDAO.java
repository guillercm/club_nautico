package com.joyfe.daw.des.rest.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyfe.daw.des.response.Barco;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class BarcoDAO implements IBarcoDAO {

	@PersistenceContext
	public EntityManager em;
	
	@Override
	public Barco getBarcoById(Long id) {
		return em.find(Barco.class, id);
	}

	@Override
	public List<Barco> getAllBarcos() {
		TypedQuery<Barco> query = em.createQuery("SELECT b FROM barcos b", Barco.class);
		return query.getResultList();
	}

	@Override
	public Barco addBarco(@RequestBody Barco barco) {
		em.persist(barco);
		return barco;
	}

	@Override
	public Barco updateBarco(Long id, Barco barco) {
		Barco barcoBBDD = getBarcoById(id);
		if (barcoBBDD != null) {
			barco.setId(id);
			return em.merge(barco);
		}
		return null;
	}

	@Override
	public Barco deleteBarco(Long id) {
		Barco barco = getBarcoById(id);
		if (barco != null) {
			em.remove(barco);
			return null;
		}
		return barco;
	}	
}