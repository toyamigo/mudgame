/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
package be.vdab.entities;
    
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 1.0: 30-09-2013 (tmtvl): Complete version.
 *          0.1: 30-09-2013 (tmtvl): Initial version.
 */
@Entity
@Table(name = "lokatie")
public class Lokatie extends HeeftItems {
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @Size(min = 1, max = 140, message = "{Size.tekst}")
    private String beschrijving;
    
    @OneToMany(mappedBy = "lokatie", fetch = FetchType.EAGER)
    private Set<Karakter> karakters;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lokatiebestemmingen", 
            joinColumns = @JoinColumn(name = "LokatieId"), 
            inverseJoinColumns = @JoinColumn(name = "BestemmingId"))
    private Set<Lokatie> bestemmingen;
    
    public Lokatie(){
        super();
        beschrijving = "";
        karakters = new LinkedHashSet<>();
        bestemmingen = new LinkedHashSet<>();
    }
    
    public Lokatie(String beschrijving){
        this();
        setBeschrijving(beschrijving);
    }
    
    public Lokatie(String beschrijving, Set<Karakter> karakters){
        this(beschrijving);
        setKarakters(karakters);
    }
    
    public Lokatie(long id, String beschrijving, Set<Karakter> karakters, Set<Item> items){
        super(items);
        setId(id);
        setBeschrijving(beschrijving);
        setKarakters(karakters);
    }
    
    public void setBeschrijving(String beschrijving){
        this.beschrijving = beschrijving;
    }
    
    public void setKarakters(Set<Karakter> karakters){
        this.karakters = karakters;
    }
    
    public void setBestemmingen(Set<Lokatie> bestemmingen){
        this.bestemmingen = bestemmingen;
    }
    
    public String getBeschrijving(){
        return beschrijving;
    }
    
    public Set<Karakter> getKarakters(){
        return karakters;
    }
    
    public Set<Lokatie> getBestemmingen(){
        return bestemmingen;
    }
    
    public void addKarakter(Karakter karakter){
        karakters.add(karakter);
        if(!equals(karakter.getLokatie())){
            karakter.setLokatie(this);
        }
    }
    
    public void removeKarakter(Karakter karakter){
        karakters.remove(karakter);
        if(equals(karakter.getLokatie())){
            karakter.setLokatie(null);
        }
    }
    
    public boolean hasKarakter(Karakter karakter){
        return karakters.contains(karakter);
    }
    
    public int getKarakterCount(){
        return karakters.size();
    }
    
    public void addBestemming(Lokatie bestemming){
        bestemmingen.add(bestemming);
        if(!bestemming.hasBestemming(this)){
            bestemming.addBestemming(this);
        }
    }
    
    public void removeBestemming(Lokatie bestemming){
        bestemmingen.remove(bestemming);
        if(bestemming.hasBestemming(this)){
            bestemming.removeBestemming(this);
        }
    }
    
    public boolean hasBestemming(Lokatie bestemming){
        return bestemmingen.contains(bestemming);
    }
    
    public int getBestemmingCount(){
        return bestemmingen.size();
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Lokatie){
            Lokatie lok = (Lokatie) o;
            if(this.getId() > 0){
                return this.getId() == lok.getId();
            }
            else {
                boolean equals;
                
                if(this.beschrijving == null){
                    equals = lok.getBeschrijving() == null;
                }
                else {
                    equals = this.beschrijving.equalsIgnoreCase(lok.getBeschrijving());
                }
                
                if(this.karakters == null && equals){
                    equals = lok.getKarakters() == null;
                }
                else if(equals){
                    equals = this.karakters.equals(lok.getKarakters());
                }
                
                if(this.bestemmingen == null && equals){
                    equals = lok.getBestemmingen() == null;
                }
                else if(equals){
                    equals = this.bestemmingen.equals(lok.getBestemmingen());
                }
                
                return equals;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        if(getId() > 0){
            return (int) getId();
        }
        else {
            String hashString = beschrijving +  karakters.toString();
            return hashString.hashCode();
        }
    }
    
}
