/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
package be.vdab.dao;
    
import be.vdab.entities.Gebruiker;
import be.vdab.entities.Karakter;
import be.vdab.entities.Lokatie;
import java.util.List;

/**
 *
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 1.0: 30-09-2013 (tmtvl): Initial version.
 */
public interface KarakterDAO {
    void create(Karakter karakter);
    Karakter read(long id);
    void update(Karakter karakter);
    void delete(long id);
    List<Karakter> findByGebruiker(Gebruiker gebruiker);
    List<Karakter> findByLokatie(Lokatie lok);
}
