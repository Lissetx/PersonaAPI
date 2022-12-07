package sadian.lisset.personaapi;

import javax.persistence.*;

@Entity
public class Characters{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String name;

    public Characters(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public Characters() {
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
