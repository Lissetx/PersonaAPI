package sadian.lisset.personaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class CharacterRestController {

    private BLLCharacter bll = new BLLCharacter();

    @Autowired
    private CharacterJpaRepository characterJpaRepository;

    @RequestMapping(path="", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void createCharacter(@RequestBody Characters character) throws IOException {
        //bll.save(character);
        //Database.createCharacter(character);
        characterJpaRepository.save(character);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public List<Characters> findAllCharacters() throws IOException {
        //return bll.findAll();
        //return Database.selectCharacters();
        return characterJpaRepository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public Optional<Characters> findCharacterById(@PathVariable int id) throws IOException { //path variable is the id in the url path
       // return bll.findById(id);
        //return Database.findCharacterById(id);
        return characterJpaRepository.findById(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deleteCharacterById(@PathVariable int id) throws IOException { //path variable is the id in the url path
        try {
            //bll.deleteById(id);
           // Database.deleteCharacter(id);
            characterJpaRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Characters updateCharacterById(@PathVariable int id, @RequestBody Characters character) throws IOException { //path variable is the id in the url path
        //bll.updateById(id, character);
        //Database.updateCharacter(id, character);
        characterJpaRepository.save(character);
        return character;
    }



}
