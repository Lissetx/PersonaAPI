package sadian.lisset.personaapi;

public class CharacterModel {
    public int id;
    public String name;

    public CharacterModel(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public CharacterModel() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



}
