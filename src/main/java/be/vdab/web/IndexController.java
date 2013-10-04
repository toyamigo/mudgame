/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
package be.vdab.web;
    
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tim Van den Langenbergh
 * @version 1.0: 30-09-2013 (tmtvl): Initial version.
 */
@Controller
@RequestMapping("/")
public class IndexController {
   
    @RequestMapping
    public String index(){
        return "index";
    }
    
}
