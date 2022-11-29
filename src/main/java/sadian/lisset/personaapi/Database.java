package sadian.lisset.personaapi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    static String url = "jdbc:mysql://localhost:3306/"
            + "personadb?allowPublicKeyRetrieval=true&useSSL=false";
    static String user = "root";
    static String password = "naruto6678";

    public static ArrayList<PersonaModel> selectPersonas() {
        String sql = "SELECT * FROM personadb.personas";
        ArrayList<PersonaModel> personas = new ArrayList<>();
        //"SELECT id, name, arcana FROM personadb.personas WHERE id=(?) and name=(?)";
        //SELECT * FROM personadb.personas
        //SELECT id, name, arcana FROM personadb.personas
        try {

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            //pst.setInt(1, 2); //1= 1st paramter, 2 = value of id
            //pst.setString(2, "Jack Frost"); 2nd parameter ? = Jack Frost
            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                System.out.print(rs.getInt("id"));
//                System.out.print(":");
//                System.out.print(rs.getString("name"));
//                System.out.print(":");
//                System.out.print(rs.getString("arcana"));
//                System.out.println();
//            }

            while (rs.next()) {
                personas.add(new PersonaModel(rs.getInt("id"), rs.getString("name"), rs.getString("arcana")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return personas;

    }

    public static void createPersona(PersonaModel persona) {
        String sql = "INSERT INTO personadb.personas (name, arcana) VALUES (?, ?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, persona.getName());
            pst.setString(2, persona.getArcana());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletePersona(int id) {
        String sql = "DELETE FROM personadb.personas WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePersona(int id, PersonaModel persona) {
        String sql = "UPDATE personadb.personas SET name = ?, arcana = ? WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, persona.getName());
            pst.setString(2, persona.getArcana());
            pst.setInt(3, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //fiND BY iD
    public static PersonaModel findPersonaById(int id) {
        String sql = "SELECT * FROM personadb.personas WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getInt("id") == id) {
                    return new PersonaModel(rs.getInt("id"), rs.getString("name"), rs.getString("arcana"));
                }
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //CHARACTERS TABLE
    public static ArrayList<CharacterModel> selectCharacters() {
        String sql = "SELECT * FROM personadb.characters";
        ArrayList<CharacterModel> characters = new ArrayList<>();
        try {

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                characters.add(new CharacterModel(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return characters;
    }

    public static void createCharacter(CharacterModel character) {
        String sql = "INSERT INTO personadb.characters (name) VALUES (?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, character.getName());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCharacter(int id) {
        String sql = "DELETE FROM personadb.characters WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCharacter(int id, CharacterModel character) {
        String sql = "UPDATE personadb.characters SET name = ? WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, character.getName());
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CharacterModel findCharacterById(int id) {
        String sql = "SELECT * FROM personadb.characters WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getInt("id") == id) {
                    return new CharacterModel(rs.getInt("id"), rs.getString("name"));
                }
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Skills Table
    public static ArrayList<SkillsModel> selectSkills() {
        String sql = "SELECT * FROM personadb.skills";
        ArrayList<SkillsModel> skills = new ArrayList<>();
        try {

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                skills.add(new SkillsModel(rs.getInt("id"), rs.getString("name"), rs.getString("power")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skills;
    }

    public static void createSkill(SkillsModel skill) {
        String sql = "INSERT INTO personadb.skills (name, power) VALUES (?, ?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, skill.getName());
            pst.setString(2, skill.getPower());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteSkill(int id) {
        String sql = "DELETE FROM personadb.skills WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateSkill(int id, SkillsModel skill) {
        String sql = "UPDATE personadb.skills SET name = ?, power = ? WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, skill.getName());
            pst.setString(2, skill.getPower());
            pst.setInt(3, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SkillsModel findSkillById(int id) {
        String sql = "SELECT * FROM personadb.skills WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getInt("id") == id) {
                    return new SkillsModel(rs.getInt("id"), rs.getString("name"), rs.getString("power"));
                }
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}






