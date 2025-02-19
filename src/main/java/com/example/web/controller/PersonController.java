package com.example.web.controller;
import com.example.web.model.Person;
import com.example.web.service.PersonService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Data
@Controller
public class PersonController {
    @Autowired
    private PersonService service;
    @GetMapping(value = {"/", "/home"})
    public ModelAndView home(Model model) {
        Iterable<Person> liste = service.getAllPersons();
        model.addAttribute("personnes", liste);
        return new ModelAndView("home");
    }
    @GetMapping("/person/{id}")
    public ModelAndView afficherFormulaire(@PathVariable Integer id, Model model) {
        Person person = service.getPerson(id);
        person = (person != null) ? person : new Person();
        model.addAttribute("person", person);
        return new ModelAndView("formulaire");
    }
    @GetMapping("/delete/{id}")
    public String afficherFormulaire(@PathVariable Integer id) {
        service.deletePerson(id);
        return "redirect:/";
    }
    @PostMapping("/sauvegarder")
    public String sauvegarderPersonne(@ModelAttribute Person person) {
        service.savePerson(person);
        return "redirect:/";
    }
}