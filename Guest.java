/*
 @Author
 Nick Salve

 22-SEP-2015
 */
package dogclasses;

public class Guest extends PetDog {

    //attributes
    private Date checkInDate;
    private String typeOfFood;
    private double amountOfFood;
    private int roomNumber = -1;
    private double dogAge;

    //default constructor
    public Guest() {
        this(1, 1, 1970, 1, 1, 1970, "unkown breed", 0.0, "unnamed", "no owner");
    }//default

    //parameter constructor
    public Guest(int cidM, int cidD, int cidY, int bdM, int bdD, int bdY,
            String breed, double weight, String name, String owner) {
        super(new Date(bdM, bdD, bdY), breed, weight, name, owner);
        this.checkInDate = new Date(cidM, cidD, cidY);
        this.determineTypeOfFood();
        this.determineAmountOfFood();
    }//parameter

    public Guest(Date checkIn, Date birthday,
            String breed, double weight, String name, String owner) {
        super(birthday, breed, weight, name, owner);
        this.checkInDate = checkIn;
        this.determineTypeOfFood();
        this.determineAmountOfFood();
    }//parameter

    //SETTERS****************************
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    //determineTypeOfFood
    public void determineTypeOfFood() {
        this.dogAge = this.getBirthDate().difference(checkInDate) / 365.25;

        if (this.dogAge >= .12 && this.dogAge < 1.5) {
            this.typeOfFood = "puppy";
        } else if (this.dogAge >= 1.5 && this.dogAge < 7) {
            this.typeOfFood = "adult";
        } else if (this.dogAge >= 7) {
            this.typeOfFood = "mature";
        } else {
            throw new IllegalArgumentException("Guest does not meet minimum age requirement" + this.dogAge);
        }

    }//determineTypeOfFood

    //determineAmountOfFood in ounces based on Dog Weight
    public void determineAmountOfFood() {
        this.amountOfFood = this.getWeight() * .25;
    }//determineAmountOfFood

    //compareTo
    public int compareTo(Guest that) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (that == null) {
            return AFTER;
        }

        if (this == that) {
            return EQUAL;
        }

        //comare rooms
        int comparison = this.roomNumber - that.roomNumber;
        if (comparison > 0) {
            return AFTER;
        } else if (comparison == 0) {
            return EQUAL;
        }

        return BEFORE;

    }//compareTO

    //GETTERS***************************
    public String getTypeOfFood() {
        return this.typeOfFood;
    }

    public double getAmountOfFood() {
        return this.amountOfFood;
    }

    public double getDogAge() {
        return dogAge / 365.25;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Date getCheckIn() {
        return this.checkInDate;
    }

    //toString
    @Override
    public String toString() {
        return String.format("%s - %s - %.2f Ounces of %s food", this.roomNumber, this.getName(),
                this.getAmountOfFood(), this.getTypeOfFood());
    }//toString

    public static void main(String args[]) {
        Guest testDog = new Guest(9, 22, 2015, 8, 28, 1985, "black lab", 55.2, "Slow Poke", "Nick Salve");
        System.out.println(testDog.getName() + " is " + testDog.dogAge + " years old.");
        System.out.println(testDog.toString());
    }
}//class

