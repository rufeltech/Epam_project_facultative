
package lib_ob;


public class Grades {
    protected Faculty faculty;
    protected Student student;
    protected String mark;

    public Grades(Faculty faculty, Student student, String mark) {
        this.faculty = faculty;
        this.student = student;
        this.mark = mark;
    }

    public Faculty get_facultative() {
        return faculty;
    }

    public String get_mark() {
        return mark;
    }

    public Student get_st() {
        return student;
    }
}
