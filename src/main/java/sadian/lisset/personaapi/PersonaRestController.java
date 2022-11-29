package sadian.lisset.personaapi;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaRestController {

    private BLLPersona bll = new BLLPersona();

    @RequestMapping(path="", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public void createPersona(@RequestBody PersonaModel persona) throws IOException {
       // bll.save(persona);
        Database.createPersona(persona);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public ArrayList<PersonaModel> findAllPersonas() throws IOException {
        //return bll.findAll();
        return Database.selectPersonas();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public PersonaModel findPersonaById(@PathVariable int id) throws IOException { //path variable is the id in the url path
       // return bll.findById(id);
        return Database.findPersonaById(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    public void deletePersonaById(@PathVariable int id) throws IOException { //path variable is the id in the url path
        try {
            //bll.deleteById(id);
            Database.deletePersona(id);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // update
    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public PersonaModel updatePersonaById(@PathVariable int id, @RequestBody PersonaModel persona) throws IOException { //path variable is the id in the url path
        //bll.updateById(id, persona);
        Database.updatePersona(id, persona);
        return persona;
    }

    //login
    @RequestMapping(path="/login", method = RequestMethod.POST)
    public void loginCheck(){
        System.out.println("login");
    }


}
