/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
package be.vdab.entities;
    
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Tim Van den Langenbergh (tmtvl)
 * @version 0.1: 30-09-2013 (tmtvl): Initial version.
 */
@Entity
@Table(name = "karakter")
public class Karakter extends HeeftItems {
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name = "gebruikerId")
    private Gebruiker gebruiker;
    
    @ManyToOne
    @JoinColumn(name = "lokatieId")
    private Lokatie lokatie;
    
    @NotNull
    @Size(min = 1, max = 50, message = "{Size.tekst}")
    private String naam;
    
    public Karakter(){
        super();
        gebruiker = null;
        naam = "";
        lokatie = null;
    }
    
    public Karakter(Gebruiker gebruiker, String naam){
        this();
        setGebruiker(gebruiker);
        setNaam(naam);
    }
    
    public Karakter(long id, Gebruiker gebruiker, String naam, 
            Lokatie lokatie, Set<Item> items){
        super(items);
        setId(id);
        setGebruiker(gebruiker);
        setNaam(naam);
        setLokatie(lokatie);
    }
    
    public void setGebruiker(Gebruiker gebruiker){
        if(this.gebruiker != null){
            this.gebruiker.removeKarakter(this);
        }
        this.gebruiker = gebruiker;
        if(gebruiker != null){
            gebruiker.addKarakter(this);
        }
    }
    
    public void setNaam(String naam){
        this.naam = naam;
    }
    
    public void setLokatie(Lokatie lokatie){
        if(this.lokatie != null && this.lokatie.hasKarakter(this)){
            this.lokatie.removeKarakter(this);
        }
        this.lokatie = lokatie;
        if(lokatie != null && !lokatie.hasKarakter(this)){
            lokatie.addKarakter(this);
        }
    }
    
    public Gebruiker getGebruiker(){
        return gebruiker;
    }
    
    public String getNaam(){
        return naam;
    }
    
    public Lokatie getLokatie(){
        return lokatie;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Karakter){
            Karakter k = (Karakter) obj;
            if(this.getId() != 0){
                return this.getId() == k.getId();
            }
            else {
                boolean equal;
                
                if(this.lokatie == null){
                    equal = k.getLokatie() == null;
                }
                else {
                    equal = this.lokatie.equals(k.getLokatie());
                }
                
                if(this.gebruiker == null && equal){
                    equal = k.getGebruiker() == null;
                }
                else if(equal){
                    equal = this.gebruiker.equals(k.getGebruiker());
                }
                
                if(this.naam == null && equal){
                    equal = k.getNaam() == null;
                }
                else if(equal){
                    equal = this.naam.equalsIgnoreCase(k.getNaam());
                }
                
                return equal;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 73 * hash + Objects.hashCode(this.gebruiker);
        hash = 73 * hash + Objects.hashCode(this.lokatie);
        hash = 73 * hash + Objects.hashCode(this.naam);
        return hash;
    }
    
}
