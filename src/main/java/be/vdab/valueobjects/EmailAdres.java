package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.*;

import be.vdab.exceptions.VerkeerdeEmailAdresException;

@Embeddable
public class EmailAdres implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String REG_EXPR = "^.+@.+\\.[a-z]+$";
	@Column(name = "EmailAdres")
	private String emailAdres;

	public EmailAdres(String emailAdres){
            setEmailAdres(emailAdres);
	}

	protected EmailAdres() {
		this.emailAdres = null;
	}
        
        protected void setEmailAdres(String emailAdres){
		if (!emailAdres.matches(REG_EXPR)) {
			throw new VerkeerdeEmailAdresException();
		}
		this.emailAdres = emailAdres;
        }

	public String getEmailAdres() {
		return emailAdres;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EmailAdres)) {
			return false;
		}
		return ((EmailAdres) obj).emailAdres.equalsIgnoreCase(this.emailAdres);
	}

	@Override
	public int hashCode() {
		return emailAdres.toUpperCase().hashCode();
	}

	@Override
	public String toString() {
		return emailAdres;
	}
}