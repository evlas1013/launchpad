/**
 *   Program Description:  a maze solver. The student must add the mouse
 *     class to the project.  Other changes to the code include:
 *     the size of the maze on line 25
 *     the position and direction of the mouse on line 26      
 *     the file name on 60
 *   Author:  Anne Applin
 *   Revised by: Nick Salve
 *   Due Date: 16-SEP
 *   Pledged:
 * 
 */
package mazesolver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author aapplin
 */
public class MazeSolver{
    public static void main(String[] args)throws FileNotFoundException  {
        char maze[][] = new char[17][17];
        Mouse mouse = new Mouse(16, 15, "north", "female");
        
        mouse.toString();
                
        readMaze(maze);
        printMaze(maze);
        System.out.println(mouse);
        while (!atExit(mouse)){
            
            if (canMoveRight(maze, mouse)){
                turnRight(mouse);                
                moveForward(maze, mouse);
            }
            else if (canMoveForward(maze, mouse)){
                moveForward(maze, mouse);                
            }
            else
                turnLeft(mouse);
            System.out.println();
            printMaze(maze);
            System.out.println(mouse);
        }
        
        System.out.println("You did it!");
    }
    /**
     * Method readmaze.  Receives a two dimensional char array and reads
     *  the contents of the specified file into the array.
     * preconditions: the maze m must be the right size to read the specified 
     * file into it.
     * @param m a character array to hold the maze
     * @throws FileNotFoundException 
     */
    public static void readMaze(char m[][])throws FileNotFoundException {
        Scanner in = null;
        char ch;
        boolean fileFound = true;
        try{
           in = new Scanner(new FileReader("data.txt"));
        }
        catch(FileNotFoundException ex){
           System.out.println("File not found.");
           fileFound = false;
        }
       if (fileFound)
            for (int r = 0; r < m.length; r++){
                String line = new String(in.nextLine());
               
                for (int c = 0; c < m[0].length; c++)
                    m[r][c] = line.charAt(c);
            }
       else {
            System.out.println("bye");
            System.exit(1);
       }
    }
    /**
     * Method printMaze prints the maze regardless of its size
     * @param m a character array that contains the maze
     */
    public static void printMaze(char m[][]){
        System.out.print("  ");
        for (int c = 0; c < m[0].length; c++)
            System.out.print(c%10);
        System.out.println();
        for (int r = 0; r < m.length; r++){
            System.out.printf("%2d",r);
            for (int c = 0; c < m[0].length; c++)
                System.out.print(m[r][c]);
            System.out.println();
        }
    }
    /**
     * Method atExit compares the mouse position to the exit position
     * @author Nick Salve
     * @param m is the Mouse object
     * @return if the mouses position matches the exit point of the maze
     *         which is hard coded depending on the maze loaded.
     */
    public static boolean atExit(Mouse m){
        if(m.getCol() == 1 && m.getRow() == 0){
            return true;
        }//if
        
        else
            return false;
       
    }//atExit
    /**
         * Method isWall accepts the maze and a row and column and tests to see
     *         if that location in the maze is a wall
     * @author Nick Salve
     * @param m the maze
     * @param r a row designation
     * @param c a column designation
     * @return the value of the comparison of the maze location [r,c] and 
     * the character   '#' which is a wall.
     */
    public static boolean isWall(char m[][], int r, int c){
        if (m[r][c] == '#'){
            return true;
        }//if
        else
            return false;
    }//isWall
    /**
          * Method canMoveRight uses a series of if-else statements so that 
     *   depending on the mouses current direction we can see if there is 
     *   a wall to the right of the mouses position
     * @author Nick Salve
     * @param m is the maze
     * @param p is the Mouse object
     * @return true if the position to the right of the mouse is not a wall
     *         false otherwise
     */
    public static boolean canMoveRight(char m[][], Mouse p){
        boolean canMove = false;
        if(p.getDirection().equalsIgnoreCase("north")){
            if (isWall(m, p.getRow(), p.getCol()+1))
                canMove = false;
            else
                canMove = true;
        }
        
        if(p.getDirection().equalsIgnoreCase("east")){
            if (isWall(m, p.getRow()+1, p.getCol()))
                canMove = false;
            else
                canMove = true;
        }
        
        if(p.getDirection().equalsIgnoreCase("south")){
            if (isWall(m, p.getRow(), p.getCol()-1))
                canMove = false;
            else
                canMove = true;
        }
        
        if(p.getDirection().equalsIgnoreCase("west")){
            if (isWall(m, p.getRow()-1, p.getCol()))
                canMove = false;
            else
                canMove = true;
        }
        
        // you write the logic for this
        return canMove;
    }
    /**
     * Method canMoveForward uses a series of if-else statements so that
     *    depending on the mouses current direction, we can see if there is 
     *    a wall directly ahead of the mouses position
     * @author Nick Salve
     * @param m  the maze
     * @param p  the Mouse object
     * @return true if the position ahead of the mouse is not a wall
     *         false otherwise
     */
    public static boolean canMoveForward(char m[][], Mouse p){
        boolean canMove = false;

        if(p.getDirection().equalsIgnoreCase("north")){
            if (isWall(m, p.getRow()-1, p.getCol()))
                canMove = false;
            else
                canMove = true;
        }
        
        if(p.getDirection().equalsIgnoreCase("east")){
            if (isWall(m, p.getRow(), p.getCol()+1))
                canMove = false;
            else
                canMove = true;
        }
        
        if(p.getDirection().equalsIgnoreCase("south")){
            if (isWall(m, p.getRow()+1, p.getCol()))
                canMove = false;
            else
                canMove = true;
        }
        
        if(p.getDirection().equalsIgnoreCase("west")){
            if (isWall(m, p.getRow(), p.getCol()-1))
                canMove = false;
            else
                canMove = true;
        }
        return canMove;
    }
    
    /**
     * Method moveForward drops a breadcrumb at the current location and then 
     *   based on the direction the mouse is facing advances it one cell in 
     *   that direction. It then prints out that the move has occurred.
     * @author Nick Salve
     * @param m  the maze
     * @param p  the Mouse object
     */
    public static void moveForward(char m[][], Mouse p){
        // drop a breadcrumb
        m[p.getRow()][p.getCol()]='.';
        
        // change a row or column to move forward based on direction
        switch(p.getDirection()){
            case "north": p.setRow(p.getRow()-1); break;
        
            case "south": p.setRow(p.getRow()+1); break;
        
            case "east": p.setCol(p.getCol()+1); break;
        
            case "west": p.setCol(p.getCol()-1); break;
        }
       
        
        System.out.printf("Moved %s", p.getDirection());
    }
    /**
    * Method turnRight changes the mouses direction based on its current
     *     direction using a switch statement it then reports to the user
     * @author Nick Salve
     * @param m the mouse
     */
    public static void turnRight(Mouse m){
        switch (m.getDirection()){
            case "north": m.setDirection("east"); break;
            case "east": m.setDirection("south"); break;
            case "south": m.setDirection("west"); break;
            case "west": m.setDirection("north"); break;
        }
        
        System.out.println("turned right ");
    }
    /**
     * Method turnLeft changes the mouses direction based on its current
     *     direction using a switch statement it then reports to the user 
     * @author Nick Salve
     * @param m the mouse
     */
    public static void turnLeft(Mouse m){
            switch (m.getDirection()){
            case "north": m.setDirection("west"); break;
            case "east": m.setDirection("north"); break;
            case "south": m.setDirection("east"); break;
            case "west": m.setDirection("south"); break;
        }
        
        System.out.println("turned left ");
    }
}//class
