/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
package be.vdab.web;
    
import be.vdab.valueobjects.EmailAdres;

/**
 *
 * @author Tim Van den Langenbergh
 * @version 0.1: 02-10-2013 (tmtvl): Initial version.
 */
public class EmailAdresForm extends EmailAdres {
    private static final long serialVersionUID = 1L;
    
    public EmailAdresForm(){
        
    }
    
    public EmailAdresForm(EmailAdres emailAdres){
        setEmailAdres(emailAdres.getEmailAdres());
    }
    
    @Override
    public void setEmailAdres(String emailAdres){
        super.setEmailAdres(emailAdres);
    }
    
}
