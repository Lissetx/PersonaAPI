package sadian.lisset.personaapi;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Personas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(length = 100, nullable = false)
    public String name;
    @Column(length = 100, nullable = false)
    public String arcana;

    @OneToMany(mappedBy = "persona")
    public List<Skills> skills = new ArrayList<>();

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

    public Personas(int id, String name, String arcana, List<Skills> skills) {
        this.id = id;
        this.name = name;
        this.arcana = arcana;
        this.skills = skills;
    }

    public Personas() {
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
