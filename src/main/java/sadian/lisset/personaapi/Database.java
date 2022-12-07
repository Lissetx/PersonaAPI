package sadian.lisset.personaapi;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    static String url = "jdbc:mysql://localhost:3306/"
            + "personadb?allowPublicKeyRetrieval=true&useSSL=false";
    static String user = "root";
    static String password = "naruto6678";

    public static ArrayList<Personas> selectPersonas() {
        String sql = "SELECT * FROM personadb.personas";
        ArrayList<Personas> personas = new ArrayList<>();
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
                personas.add(new Personas(rs.getInt("id"), rs.getString("name"), rs.getString("arcana"),
                        new ArrayList<>()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return personas;

    }

    public static void createPersona(Personas persona) {
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

    public static void updatePersona(int id, Personas persona) {
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
    public static Personas findPersonaById(int id) {
        String sql = "SELECT * FROM personadb.personas WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getInt("id") == id) {
                    return new Personas(rs.getInt("id"), rs.getString("name"), rs.getString("arcana")   ,
                            new ArrayList<>());
                }
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //CHARACTERS TABLE
    public static ArrayList<Characters> selectCharacters() {
        String sql = "SELECT * FROM personadb.characters";
        ArrayList<Characters> characters = new ArrayList<>();
        try {

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                characters.add(new Characters(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return characters;
    }

    public static void createCharacter(Characters character) {
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

    public static void updateCharacter(int id, Characters character) {
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

    public static Characters findCharacterById(int id) {
        String sql = "SELECT * FROM personadb.characters WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getInt("id") == id) {
                    return new Characters(rs.getInt("id"), rs.getString("name"));
                }
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Skills Table
    public static ArrayList<Skills> selectSkills() {
        String sql = "SELECT * FROM personadb.skills";
        ArrayList<Skills> skills = new ArrayList<>();
        try {

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                skills.add(new Skills(rs.getInt("id"), rs.getString("name"), rs.getString("power")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skills;
    }

    public static void createSkill(Skills skill) {
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

    public static void updateSkill(int id, Skills skill) {
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

    public static Skills findSkillById(int id) {
        String sql = "SELECT * FROM personadb.skills WHERE id = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getInt("id") == id) {
                    return new Skills(rs.getInt("id"), rs.getString("name"), rs.getString("power"));
                }
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    //users table
    public static ArrayList<UserModel> selectUsers() {
        String sql = "SELECT * FROM personadb.users";
        ArrayList<UserModel> users = new ArrayList<>();
        try {

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                users.add(new UserModel(rs.getString("username"), rs.getString("password")
                        , rs.getString("authority")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static void createUser(UserModel userM) {
        String sql = "INSERT INTO personadb.users (username, password, authority) VALUES (?, ?, ?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userM.getUsername());
            pst.setString(2, userM.getPassword());
            pst.setString(3, userM.getAuthority());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String username) {
        String sql = "DELETE FROM personadb.users WHERE username = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(String username, UserModel userM) {
        String sql = "UPDATE personadb.users SET username = ?, password = ?, authority = ? WHERE username = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userM.getUsername());
            pst.setString(2, userM.getPassword());
            pst.setString(3, userM.getAuthority());
            pst.setString(4, username);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findUser(String username) {
        String sql = "SELECT * FROM personadb.users WHERE username = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getString("username").equals(username)) {
                    System.out.println("Username: " + rs.getString("username") + " Password: " + rs.getString("password") + " Authority: " + rs.getString("authority"));
                }
            } else
                System.out.println("User not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}






