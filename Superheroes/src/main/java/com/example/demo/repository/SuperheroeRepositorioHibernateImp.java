package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Superheroe;

@Repository
public class SuperheroeRepositorioHibernateImp implements SuperheroeRepositorioHibernate{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Superheroe> findAll() {
		return em.createQuery("FROM Superheroe", Superheroe.class).getResultList();
	}

	@Override
	public Optional<Superheroe> findById(Integer id) {
		return Optional.of(em.find(Superheroe.class, id));
	}

	@Override
	public Superheroe save(Superheroe superheroe) {
		em.persist(superheroe);
		return superheroe;
	}

	@Override
	public void delete(Superheroe superheroe) {
		em.remove(superheroe);
	}

}
