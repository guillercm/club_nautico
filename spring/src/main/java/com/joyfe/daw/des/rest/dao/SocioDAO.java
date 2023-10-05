package com.joyfe.daw.des.rest.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joyfe.daw.des.response.Socio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class SocioDAO implements ISocioDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Socio getSocioById(Long id) {
	    return em.find(Socio.class, id);
	}

	@Override
	public List<Socio> getAllSocios() {
	    TypedQuery<Socio> query = em.createQuery("SELECT s FROM socios s", Socio.class);
	    return query.getResultList();
	}

	@Override
	public Socio addSocio(Socio socio) {
	    em.persist(socio);
	    return socio;
	}

	@Override
	public Socio updateSocio(Long id, Socio socio) {
	    Socio socioBBDD = getSocioById(id);
	    if (socioBBDD != null) {
	        socio.setId(id);
	        return em.merge(socio);
	    }
	    return null;
	}

	@Override
	public Socio deleteSocio(Long id) {
	    Socio socio = getSocioById(id);
	    if (socio != null) {
	        em.remove(socio);
	        return null;
	    }
	    return socio;
	}

}