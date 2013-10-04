/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
package be.vdab.entities;
    
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//import be.vdab.commands.Actie;
//import be.vdab.commands.HeeftActies;
//import be.vdab.commands.HerlaadActie;
//import be.vdab.commands.SchietActie;

/**
 *
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 1.0: 01-10-2013 (tmtvl): Complete version.
 *          0.1: 30-09-2013 (tmtvl): Initial version.
 */
@Entity
@Table(name = "item")
public class Item implements Serializable /*, HeeftActies*/ {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private long id;
    
    /*
     *  Dit wordt dus een ManyToOne naar IHeeftItems (wat een Karakter of een Locatie kan zijn
     */    
    @ManyToMany
    @JoinTable(name = "iseigenaarvan", 
            joinColumns = @JoinColumn(name = "itemID"), 
            inverseJoinColumns = @JoinColumn(name = "eigenaarID"))
    private Set<HeeftItems> eigenaars;
    
//    private Map<Long, Actie> acties = new HashMap<>();
    
    public Item(){
        eigenaars = new LinkedHashSet<>();
    }
    
    /*
     *  Dit wordt dus Item( ..., IHeeftItems) 
     *  
     *  Maar zowiso, indien je niet met een gemeenschappelijke basiklasse werkt, 
     *  Maak je beter 2 constructors
     *     Item( ..., Karakter k)
     *     Item( ..., Lokatie k)
     *  Nu reken je erop dat de gebruiker van je klasse 1 van beide leeg laat, maar je dwingt dit neit af!
     */
    public Item(long id, Set<HeeftItems> eigenaars){
        setId(id);
        setEigenaars(eigenaars);
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public void setEigenaars(Set<HeeftItems> eigenaars){
        this.eigenaars = eigenaars;
    }
    
//    public void setActies() {
//    	acties.put(1L, new SchietActie());
//    	acties.put(2L, new HerlaadActie(this));
//    }
    
    public long getId(){
        return id;
    }
    
    public Set<HeeftItems> getEigenaars(){
        return eigenaars;
    }
    
    public String getBeschrijving() {
    	return "Beschrijving item";
    }
    
    public void addEigenaar(HeeftItems eigenaar){
        eigenaars.add(eigenaar);
        if(!eigenaar.hasItem(this)){
            eigenaar.addItem(this);
        }
    }
    
    public void removeEigenaar(HeeftItems eigenaar){
        eigenaars.remove(eigenaar);
        if(eigenaar.hasItem(this)){
            eigenaar.removeItem(this);
        }
    }
    
    public int getEigenaarCount(){
        return eigenaars.size();
    }
    
    public boolean hasEigenaar(HeeftItems eigenaar){
        return eigenaars.contains(eigenaar);
    }
    

//	@Override
//	public Map<Long, Actie> getActies() {
//		return acties;
//	}
//	
//	public Actie getActie(long actieId) {
//	 return	acties.get(actieId);
//	}
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Item){
            Item item = (Item) obj;
            if(this.id != 0){
                return item.getId() == this.id;
            }
            else {
                if(this.eigenaars == null){
                    return item.getEigenaars() == null;
                }
                else {
                    return this.eigenaars.equals(item.getEigenaars());
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.eigenaars);
        return hash;
    }

    
}
