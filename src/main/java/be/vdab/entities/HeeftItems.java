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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 0.1: 01-10-2013 (tmtvl): Initial version.
 */
@Entity
@Table(name = "heeftitems")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class HeeftItems implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "iseigenaarvan", 
            joinColumns = @JoinColumn(name = "itemID"), 
            inverseJoinColumns = @JoinColumn(name = "eigenaarID"))
    private Set<Item> items;
    
    protected HeeftItems(){
        items = new LinkedHashSet<>();
    }
    
    protected HeeftItems(Set<Item> items){
        setItems(items);
    }
    
    protected HeeftItems(long id, Set<Item> items){
        this(items);
        setId(id);
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public long getId(){
        return id;
    }
    
    public void setItems(Set<Item> items){
        this.items = items;
    }
    
    public Set<Item> getItems(){
        return items;
    }
    
    public void addItem(Item item){
        items.add(item);
        if(!item.hasEigenaar(this)){
            item.addEigenaar(this);
        }
    }
    
    public void removeItem(Item item){
        items.remove(item);
        if(item.hasEigenaar(this)){
            item.removeEigenaar(this);
        }
    }
    
    public int getItemCount(){
        return items.size();
    }
    
    public boolean hasItem(Item item){
        return items.contains(item);
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof HeeftItems){
            HeeftItems hi = (HeeftItems) o;
            if(id != 0){
                return this.id == hi.getId();
            }
            else {
                if(this.items == null){
                    return hi.getItems() == null;
                }
                else {
                    return this.items.equals(hi.getItems());
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 11 * hash + Objects.hashCode(this.items);
        return hash;
    }
    
}
