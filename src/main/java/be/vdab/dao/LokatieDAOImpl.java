/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
package be.vdab.dao;
    
import be.vdab.entities.Lokatie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 1.0: 30-09-2013 (tmtvl): Initial version.
 */
@Repository
public class LokatieDAOImpl implements LokatieDAO {
    private EntityManager entityManager;
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    @Override
    public void create(Lokatie lokatie){
        try {
            entityManager.persist(lokatie);
        }
        catch(RuntimeException re){
            entityManager.clear();
            throw re;
        }
    }
    
    @Override
    public Lokatie read(long id){
        return entityManager.find(Lokatie.class, id);
    }
    
    @Override
    public void update(Lokatie lokatie){
        try {
            entityManager.merge(lokatie);
            entityManager.flush();
        }
        catch(RuntimeException re){
            entityManager.clear();
            throw re;
        }
    }
    
    @Override
    public void delete(long id){
        Lokatie lokatie = entityManager.find(Lokatie.class, id);
        if(lokatie != null){
            entityManager.remove(lokatie);
            entityManager.flush();
        }
    }
    
    @Override
    public List<Lokatie> findAllLokaties(){
        TypedQuery<Lokatie> query = 
                entityManager.createNamedQuery("findAllLokaties", Lokatie.class);
        return query.getResultList();
    }
    
    @Override
    public List<Lokatie> findByBestemming(Lokatie bestemming){
        TypedQuery<Lokatie> query = 
                entityManager.createNamedQuery("findLokatiesByBestemming", Lokatie.class);
        query.setParameter("bestemming", bestemming);
        return query.getResultList();
    }
    
}
