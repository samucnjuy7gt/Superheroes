package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Universo;

@Repository
public class UniversoRepositorioHibernateImp implements UniversoRepositorioHibernate{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Universo> findAll() {
		return em.createQuery("FROM Universo", Universo.class).getResultList();
	}

	@Override
	public Optional<Universo> findById(Integer id) {
		return Optional.of(em.find(Universo.class, id));
	}

	@Override
	public Universo save(Universo universo) {
		em.persist(universo);
		return universo;
	}

	@Override
	public void delete(Universo universo) {
		em.remove(universo);
	}

}
