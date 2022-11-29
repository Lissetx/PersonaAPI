package sadian.lisset.personaapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BLLSkills {
    private File file = new File("skills.json");

    private List<SkillsModel> skills = new ArrayList<>();

    public void save(SkillsModel skill) throws IOException {
//        List<CharacterModel> characters = findAll();
        skills.add(skill);
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        on.writeValue(file, skills);

        ObjectWriter ow = on.writer(new DefaultPrettyPrinter());
        ow.writeValue(file, skills);
    }

    public List<SkillsModel> findAll() throws IOException {
        if(!file.exists()){
            return skills;
        }
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        skills = on.readValue(file, new TypeReference<List<SkillsModel>>() {});
        return skills;
    }

    public SkillsModel findById(int id) throws IOException {
        for (SkillsModel skill : skills) {
            if (skill.getId() == id) {
                return skill;
            }
        }
        return null;
    }

    //DELETE MEHTOD

    public SkillsModel deleteById(int id) throws IOException {
        SkillsModel skill = findById(id);
        skills.remove(skill);
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        on.writeValue(file, skills);


        ObjectWriter ow = on.writer(new DefaultPrettyPrinter());
        ow.writeValue(file, skills);
        return skill;


    }

    public SkillsModel updateById(int id, SkillsModel skill) throws IOException {
        for(int i = 0; i < skills.size(); i++){
            if(skills.get(i).getId() == id){
                skills.get(i).setName(skill.getName());
                skills.get(i).setPower(skill.getPower());
            }
        }
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        on.writeValue(file, skills);

        ObjectWriter ow = on.writer(new DefaultPrettyPrinter());
        ow.writeValue(file, skills);
        return skill;


    }

}
