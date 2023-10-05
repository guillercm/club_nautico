package com.joyfe.daw.des.rest.dao;



import java.util.List;

import org.springframework.stereotype.Repository;
import com.joyfe.daw.des.response.Salida;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class SalidaDAO implements ISalidaDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Salida getSalidaById(Long id) {
		return em.find(Salida.class, id);
	}

	@Override
	public List<Salida> getAllSalidas() {
		TypedQuery<Salida> query = em.createQuery("SELECT s FROM salidas s", Salida.class);
		return query.getResultList();
	}

	@Override
	public Salida addSalida(Salida salida) {
		System.out.println(salida.getId() + "\n\n\n");
		em.persist(salida);
		System.out.println(salida.getId() + "\n\n\n");
		return salida;
	}

	@Override
	public Salida updateSalida(Long id, Salida salida) {
		Salida salidaBBDD = getSalidaById(id);
		if (salidaBBDD != null) {
			salida.setId(id);
			return em.merge(salida);
		}
		return null;
	}

	@Override
	public Salida deleteSalida(Long id) {
		Salida salida = getSalidaById(id);
		if (salida != null) {
			em.remove(salida);
			return null;
		}
		return salida;
	}	
}
