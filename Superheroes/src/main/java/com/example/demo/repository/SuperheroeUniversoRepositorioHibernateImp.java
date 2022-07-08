package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.SuperheroeUniversoKey;

@Repository
public class SuperheroeUniversoRepositorioHibernateImp implements SuperheroeUniversoRepositorioHibernate{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<SuperheroeUniverso> findAll() {
		return em.createQuery("FROM SuperheroeUniverso", SuperheroeUniverso.class).getResultList();
	}

	@Override
	public Optional<SuperheroeUniverso> findById(SuperheroeUniversoKey id) {
		return Optional.of(em.find(SuperheroeUniverso.class, id));
	}

	@Override
	public SuperheroeUniverso save(SuperheroeUniverso superheroeUniverso) {
		em.persist(superheroeUniverso);
		return superheroeUniverso;
	}

	@Override
	public void delete(SuperheroeUniverso superheroeUniverso) {
		em.remove(superheroeUniverso);
	}

}
