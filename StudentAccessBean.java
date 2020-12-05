package Project351;

import java.io.Serializable;

public class StudentAccessBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String Fname;
    private String Lname;
    private String LI;
    private String LO;
    private String TTA;
    private int ID;
    private int Mark;
    

    public String getFname() {
        return Fname;
    }

    public void setFname(String f) {
        this.Fname = f;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String l) {
        this.Lname = l;
    }
    
    public String getLI() {
        return LI;
    }

    public void setLI(String l) {
        this.LI = l;
    }
    
    public String getLO() {
        return LO;
    }

    public void setLO(String l) {
        this.LO = l;
    }
    
    public String getTTA() {
        return TTA;
    }

    public void setTTA(String l) {
        this.TTA = l;
    }
    
    public int getID() {
        return ID;
    }
    
    public void setID(int id) {
        this.ID = id;
    }
    
    public int getMark() {
        return Mark;
    }
    
    public void setMark(int m) {
        this.Mark = m;
    }
    
    
}