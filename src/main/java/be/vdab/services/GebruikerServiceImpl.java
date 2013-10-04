/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.services;

import be.vdab.dao.GebruikerDAO;
import be.vdab.entities.Gebruiker;
import be.vdab.exceptions.EmailAdresAlInGebruikException;
import be.vdab.exceptions.GebruikerHeeftNogKaraktersException;
import be.vdab.valueobjects.EmailAdres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 1.0: 30-09-2013 (tmtvl): Initial version.
 */

@Service
@Transactional(readOnly = true)
public class GebruikerServiceImpl implements GebruikerService {
	private final GebruikerDAO gebruikerDAO;

	@Autowired
	public GebruikerServiceImpl(GebruikerDAO gebruikerDAO) {
		this.gebruikerDAO = gebruikerDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void create(Gebruiker gebruiker) {
		try {
			gebruikerDAO.create(gebruiker);
		} catch (DataIntegrityViolationException e) {
			if (gebruikerDAO.findByEmail(gebruiker.getEmailAdres()) != null) {
				throw new EmailAdresAlInGebruikException();
			}
			throw e;
		}
	}

	@Override
	public Gebruiker read(long id) {
		return gebruikerDAO.read(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Gebruiker gebruiker) {
		try {
			gebruikerDAO.update(gebruiker);
		} catch (DataIntegrityViolationException e) {
			if (gebruikerDAO.findByEmail(gebruiker.getEmailAdres()) != null) {
				throw new EmailAdresAlInGebruikException();
			}
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(long id) {
		Gebruiker gebruiker = gebruikerDAO.read(id);
		if (!gebruiker.getKarakter().isEmpty()) {
			throw new GebruikerHeeftNogKaraktersException();
		}
		gebruikerDAO.delete(id);
	}

	@Override
	public Gebruiker findByEmail(EmailAdres email) {
		return gebruikerDAO.findByEmail(email);
	}

}
