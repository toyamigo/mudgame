/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
/*
 * Git test Door StijnVA --verwijder deze commentaar gerust
 */
	
package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import be.vdab.util.Paswoord;
import be.vdab.valueobjects.EmailAdres;
import java.util.LinkedHashSet;
import javax.persistence.FetchType;

@Entity
@Table(name = "gebruiker")
public class Gebruiker implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@NotNull
	@Size(min = 1, max = 50, message = "{Size.tekst}")
	private String voornaam;
	@NotNull
	@Size(min = 1, max = 50, message = "{Size.tekst}")
	private String familienaam;
	@NotNull
	@Size(min = 1, max = 50, message = "{Size.tekst}")
	private String paswoord;

	@OneToMany(mappedBy = "gebruiker", fetch = FetchType.EAGER)
	private Set<Karakter> karakters = new LinkedHashSet<>();

	public Set<Karakter> getKarakter() {
		return Collections.unmodifiableSet(karakters);
	}

	public void addKarakter(Karakter karakter) {
		karakters.add(karakter);
	}

	public void removeKarakter(Karakter karakter) {
		karakters.remove(karakter);
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public String getPaswoord() {
		return paswoord;
	}

	public void setPaswoord(String paswoord) {
		String userName = this.emailAdres.toString();
		this.paswoord = Paswoord.paswoordEncoder(paswoord, userName);
	}

	public long getId() {
		return id;
	}

	public Gebruiker() {
            
	}

	public Gebruiker(String voornaam, String familienaam, EmailAdres emailAdres,
			String paswoord) {
                this();
		this.setVoornaam(voornaam);
		this.setFamilienaam(familienaam);
		this.setEmailAdres(emailAdres);
		this.setPaswoord(paswoord);
	}

	public Gebruiker(String voornaam, String familienaam, String paswoord) {
                this();
		this.setVoornaam(voornaam);
		this.setFamilienaam(familienaam);
		this.setPaswoord(paswoord);
	}

	public Gebruiker(Gebruiker gebruiker) {
                this();
		this.voornaam = gebruiker.getVoornaam();
		this.familienaam = gebruiker.getFamilienaam();
		this.paswoord = gebruiker.getPaswoord();
		this.emailAdres = gebruiker.getEmailAdres();
                for(Karakter k : gebruiker.karakters){
                    this.addKarakter(k);
                }
	}

	@NotNull
	@Valid
	@Embedded
	private EmailAdres emailAdres;

	public EmailAdres getEmailAdres() {
		return emailAdres;
	}

	public void setEmailAdres(EmailAdres emailAdres) {
		this.emailAdres = emailAdres;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ ((emailAdres == null) ? 0 : emailAdres.hashCode());
//		return result;
//	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Gebruiker other = (Gebruiker) obj;
//		if (emailAdres == null) {
//			if (other.emailAdres != null)
//				return false;
//		} else if (!emailAdres.equals(other.emailAdres))
//			return false;
//		return true;
//	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Gebruiker)) {
			return false;
		}
                else {
                    if(emailAdres == null){
                        return ((Gebruiker) obj).emailAdres == null;
                    }
                    return ((Gebruiker) obj).emailAdres.equals(this.emailAdres);
                }
	}

	@Override
	public int hashCode() {
            if(emailAdres == null){
                return 0;
            }
		return emailAdres.hashCode();
	}

	
	
}
