/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
package be.vdab.services;
    
import be.vdab.entities.Gebruiker;
import be.vdab.valueobjects.EmailAdres;

/**
 *
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 1.0: 30-09-2013 (tmtvl): Initial version.
 */
public interface GebruikerService {
    void create(Gebruiker gebruiker);
    Gebruiker read(long id);
    void update(Gebruiker gebruiker);
    void delete(long id);
    Gebruiker findByEmail(EmailAdres email);
}
