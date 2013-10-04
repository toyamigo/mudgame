/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
package be.vdab.dao;
    
import be.vdab.entities.Lokatie;
import java.util.List;

/**
 *
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 1.0: 30-09-2013(tmtvl): Initial version.
 */
public interface LokatieDAO {
    void create(Lokatie lokatie);
    Lokatie read(long id);
    void update(Lokatie lokatie);
    void delete(long id);
    List<Lokatie> findAllLokaties();
    List<Lokatie> findByBestemming(Lokatie bestemming);
}
