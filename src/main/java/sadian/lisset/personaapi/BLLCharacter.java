package sadian.lisset.personaapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BLLCharacter {
    private File file = new File("characters.json");

    private List<CharacterModel> characters = new ArrayList<>();

    public void save(CharacterModel character) throws IOException {
//        List<CharacterModel> characters = findAll();
        characters.add(character);
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        on.writeValue(file, characters);

        ObjectWriter ow = on.writer(new DefaultPrettyPrinter());
        ow.writeValue(file, characters);
    }

    public List<CharacterModel> findAll() throws IOException {
        if(!file.exists()){
            return characters;
        }
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        characters = on.readValue(file, new TypeReference<List<CharacterModel>>() {});
        return characters;
    }

    public CharacterModel findById(int id) throws IOException {
        for (CharacterModel character : characters) {
            if (character.getId() == id) {
                return character;
            }
        }
        return null;
    }

    //DELETE MEHTOD

    public void deleteById(int id) throws IOException {
        CharacterModel character = findById(id);
        characters.remove(character);
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        on.writeValue(file, characters);


        ObjectWriter ow = on.writer(new DefaultPrettyPrinter());
        ow.writeValue(file, characters);



    }

    public CharacterModel updateById(int id, CharacterModel character) throws IOException {

        for(int i =0; i < characters.size(); i++){
            if(characters.get(i).getId() == id){
                characters.get(i).setName(character.getName());

            }
        }
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        on.writeValue(file, characters);


        ObjectWriter ow = on.writer(new DefaultPrettyPrinter());
        ow.writeValue(file, characters);
        return character;


    }


}
