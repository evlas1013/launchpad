package mazesolver;

/**
 *
 * @author Nick Salve
 * Due 16-SEP
 * A mouse object which can navigate a maze. Methods include Set and Get for
 * Column Position
 * Row Position
 * and Direction
 * 
 * Default constructor sets row and col positions to 0,0, heading North. Female.
 *
 */
public class Mouse {
    private int colPosition;
    private int rowPosition;
    private String direction;
    private String gender;
    
    public Mouse (int rowPosition, int colPosition, String direction, String gender){
        this.colPosition = colPosition;
        this.rowPosition = rowPosition;
        this.direction = direction;
        this.gender = gender;
    }//constructor
    
    //default constructor
    public Mouse (){
        this.colPosition = 0;
        this.rowPosition = 0;
        this.direction = "north";
        this.gender = "female";
    }
    
    
    public String toString(){
        return "Current position is:\nRow\t[" + this.rowPosition + "]\n"
                + "Column\t[" + this.colPosition + "]\n"
                + "Headed\t[" + this.direction + "]\n";
    }//toString
    
    public int getCol(){
        return this.colPosition;
    }//getCol
    
    public void setCol(int i){
        this.colPosition = i;
    }//setCol
    
    public void setRow (int i){
        this.rowPosition = i;
    }//setRow
    
    public void setDirection(String direction){
        this.direction = direction;
    }//setDirection
    
    public int getRow(){
        return this.rowPosition;
    }//getRow
    
    public String getDirection(){
        return this.direction;
    }//getDirection
    
    
}//mouse class
