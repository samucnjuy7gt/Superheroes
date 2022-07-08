package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Poder;

@Repository
public class PoderRepositorioHibernateImp implements PoderRepositorioHibernate{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Poder> findAll() {
		return em.createQuery("FROM Poder", Poder.class).getResultList();
	}

	@Override
	public Optional<Poder> findById(Integer id) {
		return Optional.of(em.find(Poder.class, id));
	}

	@Override
	public Poder save(Poder poder) {
		em.persist(poder);
		return poder;
	}

	@Override
	public void delete(Poder poder) {
		em.remove(poder);
	}

}
