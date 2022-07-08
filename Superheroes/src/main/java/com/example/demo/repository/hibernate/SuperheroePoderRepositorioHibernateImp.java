package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroePoderKey;

@Repository
public class SuperheroePoderRepositorioHibernateImp implements SuperheroePoderRepositorioHibernate{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<SuperheroePoder> findAll() {
		return em.createQuery("FROM SuperheroePoder", SuperheroePoder.class).getResultList();
	}

	@Override
	public Optional<SuperheroePoder> findById(SuperheroePoderKey id) {
		return Optional.of(em.find(SuperheroePoder.class, id));
	}

	@Override
	public SuperheroePoder save(SuperheroePoder superheroePoder) {
		em.persist(superheroePoder);
		return superheroePoder;
	}

	@Override
	public void delete(SuperheroePoder superheroePoder) {
		em.remove(superheroePoder);
	}

}
