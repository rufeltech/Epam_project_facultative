
package lib_ob;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    protected List<Grades> mark_list = new ArrayList<>();

    public void addMark(Grades mark) {
        this.mark_list.add(mark);
    }

    public Student(String name, String login, String password) {
        super(name, login, password);
    }

    public Student(String name, String login, String password, List<Faculty> facultyList) {
        super(name, login, password, facultyList);
    }

    public Student(int id, String name, String login, String password, List<Faculty> facultyList) {
        super(id, name, login, password, facultyList);
    }

    public Student(String name, String login, String password, List<Faculty> facultyList, List<Grades> markList) {
        super(name, login, password, facultyList);
        this.mark_list = markList;
    }

    public Student(int id, String name, String login, String password, List<Faculty> facultyList, List<Grades> markList) {
        super(id, name, login, password, facultyList);
        this.mark_list = markList;
    }

    public Student(int id, String name, String login, String password) {
        super(id, name, login, password);
    }

    public void set_mark_list(List<Grades> markList) {
        this.mark_list = markList;
    }
    public Grades get_mark_facult(Faculty faculty) {
        for (Grades mark : mark_list) {
            if (mark.get_facultative().id==  faculty.get_id()) return mark;
        }
        return null;
    }
   


}
