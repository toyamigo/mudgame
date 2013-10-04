package be.vdab.web;

import be.vdab.entities.Gebruiker;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Karakter;
import be.vdab.entities.Lokatie;
import be.vdab.exceptions.KarakterNaamAlInGebruikException;
import be.vdab.services.GebruikerService;
import be.vdab.services.KarakterService;
import be.vdab.services.LokatieService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/karakter")
public class KarakterController {
    private final GebruikerService gebruikerService;
	private final KarakterService karakterService;
	private final LokatieService lokatieService;
	
        @Autowired
	public KarakterController(KarakterService karakterService, LokatieService lokatieService, 
                    GebruikerService gebruikerService) {
		this.karakterService = karakterService;
		this.lokatieService = lokatieService;
                this.gebruikerService = gebruikerService;
	}
	
        @RequestMapping(method = RequestMethod.POST)
        public String create(@Valid Karakter karakter, BindingResult bindingResult, 
                HttpSession session){
            if(!bindingResult.hasErrors()){
                Gebruiker g = (Gebruiker) session.getAttribute("gebruiker");
                try {
                    karakter.setGebruiker(g);
                    karakter.setLokatie(lokatieService.findAllLokaties().get(0));
                    karakterService.create(karakter);
                    return "redirect:/hoofdmenu";
                }
                catch(KarakterNaamAlInGebruikException knaige){
                    bindingResult.rejectValue("naam", "{KarakterNaamBestaatAlException}");
                }
            }
            return "karakters/karaktergeneratie";
        }
        
        @RequestMapping(value = "nieuw", method = RequestMethod.GET)
        public ModelAndView createForm(HttpSession session){
            Karakter k = new Karakter();
            Gebruiker g = (Gebruiker) session.getAttribute("gebruiker");
            k.setLokatie(lokatieService.findAllLokaties().get(0));
            
            if(g == null){
                return new ModelAndView("redirect:/hoofdmenu");
            }
            
            if(g.getKarakter().size() >= 1){
                return new ModelAndView("redirect:/hoofdmenu");
            }
            
            k.setGebruiker(g);
            
            return new ModelAndView("karakters/karaktergeneratie", 
                    "karakter", k);
        }
        
        @RequestMapping(method = RequestMethod.DELETE)
        public String delete(@RequestParam long karakterId, HttpSession session){
            Karakter k = karakterService.read(karakterId);
            Gebruiker g = k.getGebruiker();
            k.setGebruiker(null);
            g.removeKarakter(k);
            karakterService.delete(karakterId);
            session.setAttribute("gebruiker", g);
            return "redirect:/hoofdmenu";
        }

	@RequestMapping(value="/karakters", method = RequestMethod.GET)
	public ModelAndView findAllKaraktersInLokatie(@RequestParam long lokatieId) {
		ModelAndView mav = new ModelAndView("karakters");
		Lokatie lokatie = lokatieService.read(lokatieId);
		mav.addObject("karakters/karaktersInLokatie", karakterService.findByLokatie(lokatie));
		return mav;
	}
	
	@RequestMapping(value = "jsonlijstPerLocatie", produces = {"application/json"})
	public List<Karakter> showJSONAllKaraktersInLokatie(@RequestParam long lokatieId) {
		Lokatie lokatie = lokatieService.read(lokatieId);
		return karakterService.findByLokatie(lokatie);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "lijstperlocatie")
	public ModelAndView showAllKaraktersInLokatie(@RequestParam long lokatieId) {
		ModelAndView mav = new ModelAndView("karakters/lijstPerLocatie");
		return mav;
	}
	
	@RequestMapping(value="eigenschappen", method = RequestMethod.GET)
	public ModelAndView findKarakter(@RequestParam long karakterId) {
		ModelAndView mav = new ModelAndView("karakters");
		mav.addObject("karakter/eigenschappen", karakterService.read(karakterId));
		return mav;
	}
	
	
}
