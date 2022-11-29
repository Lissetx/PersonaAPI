package sadian.lisset.personaapi;

public class PersonaModel {
    public int id;
    public String name;
    public String arcana;

    public PersonaModel(int id, String name, String arcana) {
        this.id = id;
        this.name = name;
        this.arcana = arcana;
    }

    public PersonaModel() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArcana(String arcana) {
        this.arcana = arcana;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArcana() {
        return arcana;
    }
}
