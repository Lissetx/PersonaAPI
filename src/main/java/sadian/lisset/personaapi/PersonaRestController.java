package sadian.lisset.personaapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaRestController {

    //THIS AND OTHER CONTROLLERS CONTAIN 3 DIFFERENT WAYS OF DATA STORAGE
    //1. IN MEMORY  -- BLL  --STORING JSON ON LOCAL MACHINE
    //2. DATABASE -- SQL -- STORING DATA IN SQL DATABASE (the hardcoded  way)
    //3. DATABASE -- SQL -- STORING DATA IN SQL DATABASE (the dynamic way)

    private BLLPersona bll = new BLLPersona();

    @Autowired
    private PersonaJpaRepository personaJpaRepository;

    @RequestMapping(path="", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void createPersona(@RequestBody Personas persona) throws IOException {
       // bll.save(persona);
        //Database.createPersona(persona);
        personaJpaRepository.save(persona);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public ArrayList<Personas> findAllPersonas() throws IOException {
        //return bll.findAll();
        //return Database.selectPersonas();
        return (ArrayList<Personas>) personaJpaRepository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public Personas findPersonaById(@PathVariable int id) throws IOException { //path variable is the id in the url path
        // return bll.findById(id);
        // return Database.findPersonaById(id);
        Optional<Personas> result = personaJpaRepository.findById(id);
        //I don't understand why it has to be string -- ask tutor
        return result.orElse(null);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deletePersonaById(@PathVariable int id) throws IOException { //path variable is the id in the url path
        try {
            //bll.deleteById(id);
           // Database.deletePersona(id);
            personaJpaRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // update
    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Personas updatePersonaById(@PathVariable int id, @RequestBody Personas persona) throws IOException { //path variable is the id in the url path
        //bll.updateById(id, persona);
       // Database.updatePersona(id, persona);
        Personas personaToUpdate = personaJpaRepository.findById(id).get();
        personaToUpdate.setName(persona.getName());
        personaToUpdate.setArcana(persona.getArcana());
        personaJpaRepository.save(persona);
        return persona;
    }

    //login
    @RequestMapping(path="/login", method = RequestMethod.POST)
    public void loginCheck(){
        System.out.println("login");
    }


}
