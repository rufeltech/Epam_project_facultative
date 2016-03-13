
package lib_ob;
public class Course {
   
    protected int id;
   
    protected String title;
  
    public Course(int id, String title) {

        this.id = id;
        this.title = title;
        
        
    }
    public String get_title()
    {return title;}
    public int get_id()
    {
    		return id;
    }
}
