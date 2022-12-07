package sadian.lisset.personaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sadian.lisset.personaapi.BLLSkills;
import sadian.lisset.personaapi.Skills;
import sadian.lisset.personaapi.SkillsJpaRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skills")
public class SkillsRestController {

   private BLLSkills bll = new BLLSkills();

    @Autowired
    private SkillsJpaRepository skillsJpaRepository;

    @RequestMapping(path="", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void createSkill(@RequestBody Skills skill) throws IOException {
        //bll.save(skill);
        //Database.createSkill(skill);
        skillsJpaRepository.save(skill);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public List<Skills> findAllSkills() throws IOException {
        //return bll.findAll();
       // return Database.selectSkills();
        return skillsJpaRepository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public Skills findSkillById(@PathVariable int id) throws IOException { //path variable is the id in the url path
        //return bll.findById(id);
        //return Database.findSkillById(id);
        Optional<Skills> result = skillsJpaRepository.findById(id);
        return result.orElse(null);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deleteSkillById(@PathVariable int id) throws IOException { //path variable is the id in the url path
        try {
            //bll.deleteById(id);
            //Database.deleteSkill(id);
            skillsJpaRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Skills updateSkillById(@PathVariable int id, @RequestBody Skills skill) throws IOException { //path variable is the id in the url path
       // bll.updateById(id, skill);
        //Database.updateSkill(id, skill);
        skillsJpaRepository.save(skill);
        return skill;
    }

}
