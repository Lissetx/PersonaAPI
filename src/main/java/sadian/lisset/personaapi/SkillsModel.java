package sadian.lisset.personaapi;

public class SkillsModel {
    public int id;
    public String name;
    public String power;

    public SkillsModel(int id, String name, String power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public SkillsModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
