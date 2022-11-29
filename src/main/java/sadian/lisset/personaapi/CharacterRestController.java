package sadian.lisset.personaapi;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterRestController {

    private BLLCharacter bll = new BLLCharacter();

    @RequestMapping(path="", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public void createCharacter(@RequestBody CharacterModel character) throws IOException {
        //bll.save(character);
        Database.createCharacter(character);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public List<CharacterModel> findAllCharacters() throws IOException {
        //return bll.findAll();
        return Database.selectCharacters();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public CharacterModel findCharacterById(@PathVariable int id) throws IOException { //path variable is the id in the url path
       // return bll.findById(id);
        return Database.findCharacterById(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    public void deleteCharacterById(@PathVariable int id) throws IOException { //path variable is the id in the url path
        try {
            //bll.deleteById(id);
            Database.deleteCharacter(id);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public CharacterModel updateCharacterById(@PathVariable int id, @RequestBody CharacterModel character) throws IOException { //path variable is the id in the url path
        //bll.updateById(id, character);
        Database.updateCharacter(id, character);
        return character;
    }



}
