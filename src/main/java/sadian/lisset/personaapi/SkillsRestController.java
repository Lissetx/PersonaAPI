package sadian.lisset.personaapi;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsRestController {

   private BLLSkills bll = new BLLSkills();

    @RequestMapping(path="", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public void createSkill(@RequestBody SkillsModel skill) throws IOException {
        //bll.save(skill);
        Database.createSkill(skill);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public List<SkillsModel> findAllSkills() throws IOException {
        //return bll.findAll();
        return Database.selectSkills();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public SkillsModel findSkillById(@PathVariable int id) throws IOException { //path variable is the id in the url path
        //return bll.findById(id);
        return Database.findSkillById(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    public void deleteSkillById(@PathVariable int id) throws IOException { //path variable is the id in the url path
        try {
            //bll.deleteById(id);
            Database.deleteSkill(id);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public SkillsModel updateSkillById(@PathVariable int id, @RequestBody SkillsModel skill) throws IOException { //path variable is the id in the url path
       // bll.updateById(id, skill);
        Database.updateSkill(id, skill);
        return skill;
    }

}
