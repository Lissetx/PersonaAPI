package sadian.lisset.personaapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BLLPersona {
    private File file = new File("personas.json");

    private List<Personas> personas = new ArrayList<>();

    public void save(Personas persona) throws IOException {
//        List<PersonaModel> personas = findAll();

        personas.add(persona);
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        on.writeValue(file, personas);

        ObjectWriter ow = on.writer(new DefaultPrettyPrinter());
        ow.writeValue(file, personas);
    }

    public List<Personas> findAll() throws IOException {
        if(!file.exists()){
            return personas;
        }
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        personas = on.readValue(file, new TypeReference<List<Personas>>() {});
       return personas;
    }

    public Personas findById(int id) throws IOException {
        for (Personas persona : personas) {
            if (persona.getId() == id) {
                return persona;
            }
        }
        return null;
    }

//DELETE MEHTOD
    public Personas deleteById(int id) throws IOException {

        Personas persona = findById(id);
        personas.remove(persona);
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        on.writeValue(file, personas);

        ObjectWriter ow = on.writer(new DefaultPrettyPrinter());
        ow.writeValue(file, personas);
        return persona;
    }


    public Personas updateById(int id, Personas persona) throws IOException {
        System.out.println("updateById: " + id);

        //find the index of the persona to update then set new name and arcana

        for(int i = 0; i < personas.size(); i++){
            if(personas.get(i).getId() == id){
                personas.get(i).setName(persona.getName());
                personas.get(i).setArcana(persona.getArcana());
            }
        }
        ObjectMapper on = new ObjectMapper();
        on.findAndRegisterModules(); // use this if using less common types like LocalDateTime
        on.writeValue(file, personas);

        ObjectWriter ow = on.writer(new DefaultPrettyPrinter());
        ow.writeValue(file, personas);
        return persona;
    }



}
