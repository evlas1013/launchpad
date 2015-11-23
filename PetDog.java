/*
* Course: CSCI 160
* Class: Dog
* Uses: Date Class
* Extends: Dog
* Implements: Nothing
* Presumption: All Dogs have these attributes
 */
package dogclasses;

/**
 *
 * @author Evlas
 */
public class PetDog extends Dog {
    
    private String name;
    private String owner;
    
    //default constructor
    public PetDog(){
        this.setBirthDate(new Date(0,0,0));
        this.setBreed("none");
        this.setWeight(0.0);
        this.name = "unnamed";
        this.owner = "no owner";
    }//default constructor
    
    public PetDog(Date birthDate, String breed, double weight, String name,
        String owner){
        super(birthDate, breed, weight);
        this.name = name;
        this.owner = owner;
    }//parameter constructor

    //GETTERS***************
    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    
    //SETTERS***************
    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString(){
        return String.format("%s%-5s%-15s%-15s", super.toString(), " ", name, owner);
    }//toString()
    
    
    public static void main (String [] args){
        PetDog dog1 = new PetDog();
        Date dog2Date = new Date(9,15,2010);
        PetDog dog2 = new PetDog(dog2Date, "Border Collie", 24.9, "Phoebe", 
                "Nick Salve");
        
        System.out.println(dog1);
        System.out.println(dog2);
    }//main
            
}//end class
