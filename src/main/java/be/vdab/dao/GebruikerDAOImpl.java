/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import be.vdab.entities.Gebruiker;
import be.vdab.valueobjects.EmailAdres;

/**
 * 
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 1.0: 30-09-2013(tmtvl): Initial version.
 */
@Repository
public class GebruikerDAOImpl implements GebruikerDAO {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(Gebruiker gebruiker) {
		try {
			entityManager.persist(gebruiker);
		} catch (RuntimeException e) {
			entityManager.clear();
			throw e;
		}
	}

	@Override
	public Gebruiker read(long id) {
		return entityManager.find(Gebruiker.class, id);
	}

	@Override
	public void update(Gebruiker gebruiker) {
		try {
			entityManager.merge(gebruiker);
			entityManager.flush();
		} catch (RuntimeException e) {
			entityManager.clear();
			throw e;
		}
	}

	@Override
	public void delete(long id) {
		Gebruiker gebruiker = entityManager.find(Gebruiker.class, id);
		if (gebruiker != null) {
			entityManager.remove(gebruiker);
			entityManager.flush();
		}
	}

	@Override
	public Gebruiker findByEmail(EmailAdres emailAdres) {
		TypedQuery<Gebruiker> query = entityManager.createNamedQuery(
				"findGebruikerByEmailAdres", Gebruiker.class);
		query.setParameter("emailAdres", emailAdres);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
