package lib_ob;

import java.util.ArrayList;
import java.util.List;


public class User {
  
    protected int id;

    protected String name;
 
    protected String login;
  
    protected String password;
  
    protected List<Faculty> facult_list = new ArrayList<>();

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String name, String login, String password, List<Faculty> facultyList) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.facult_list = facultyList;
    }

    public User(int id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }
    

    public User(int id, String name, String login, String password, List<Faculty> facultyList) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.facult_list = facultyList;
    }

 
    public int get_id() {
        return id;
    }

    public String get_log() {
        return login;
    }

    public String get_passw() {
        return password;
    }

    public String get_name() {
        return name;
    }

    public void set_facultative_list(List<Faculty> facultyList) {
        this.facult_list = facultyList;
    }

   

  
}
