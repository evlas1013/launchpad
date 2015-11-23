/*
 @Author
 Nick Salve

 22-SEP-2015
 */
package dogclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Evlas
 */
public class Hotel {

    //attributes
    private String hotelName;
    private String hotelAddress;
    private double dailyRate;
    private int capacity;
    private List<Guest> guestList;
    private int nextEmptyRoom;

    /**
     *
     * @param hotelName
     * @param hotelAddress
     * @param dailyRate
     * @param capacity
     */
    public Hotel(String hotelName, String hotelAddress, double dailyRate, int capacity) {
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.dailyRate = dailyRate;
        this.capacity = capacity;
        guestList = new ArrayList();
    }//constructor

    //addGuest
    /**
     *
     * @param guest
     * @param room
     * @return
     */
    public boolean addGuest(Guest guest, int room) {
        if (guestList.size() < this.capacity) {
            guestList.add(room, guest);
            return true;
        } else {
            return false;
        }
    }//addGuest

    /**
     *
     * @param location
     */
    public void removeGuest(int location) {
        guestList.remove(location);
    }

    /**
     *
     * @param checkIn
     * @param birthday
     * @param name
     * @param owner
     * @param weight
     * @param age
     * @param emptyRoom
     * @param breed
     * @return
     */
    public int checkInGuest(Date checkIn, Date birthday, String name, String owner, double weight, double age,
            int emptyRoom, String breed) {
        if (guestList.size() < capacity) {
            Guest newArrival = new Guest(checkIn, birthday, breed, weight, name, owner);
            addGuest(newArrival, emptyRoom);
            newArrival.setRoomNumber(emptyRoom);
            return newArrival.getRoomNumber();
        } else {
            return -1;
        }

    }//checkInGuest

    /**
     *
     * @param ownerName
     * @return
     */
    public Guest findGuest(String ownerName) {
        int i = 0;
        while (i < guestList.size() && !guestList.get(i).getOwner().equalsIgnoreCase(ownerName)) {
            i++;
        }
        return guestList.get(i);
    }

    //findEmptyRoom
    /**
     *
     * @return
     */
    public int findEmptyRoom() {
        int i = 0;
        while (i < guestList.size() && guestList.get(i).getRoomNumber() == i) {
            System.out.printf("Room %d is full\n", i);
            i++;
        }
        System.out.printf("Room %d is available\n", i);
        return i;

    }

    /**
     *
     * @param hotelName
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     *
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @param rate
     */
    public void setRate(double rate) {
        this.dailyRate = rate;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.hotelName;
    }

    /**
     *
     * @return
     */
    public Double getRates() {
        return this.dailyRate;
    }

    /**
     *
     * @return
     */
    public List<Guest> getGuestList() {
        return guestList;
    }

    /**
     *
     * @return
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     *
     * @param list
     */
    public void overWriteGuestList(ArrayList<Guest> list) {
        //delete the original list
        for (int i = 0; i < this.guestList.size(); i++) {
            this.guestList.remove(i);
        }
        //write in all the new data
        for (int i = 0; i < list.size(); i++) {
            this.guestList.add(list.get(i));

        }

    }

    /**
     *
     * @return
     */
    public String saveData() {
        String data = "";
        String lineBreak = System.getProperty("line.separator");
        data += String.format(
                "%s"//name
                + lineBreak
                + "%f"//rate
                + "\t"
                + "%d"//capacity
                + lineBreak, this.getName(), this.getRates(), this.getCapacity()
        );
        for (int i = 0; i < guestList.size(); i++) {
            Guest g = guestList.get(i);
            data += String.format(
                    "%s" //name
                    + "\t"
                    + "%d" //birthday
                    + "\t"
                    + "%d" //birthmonth
                    + "\t"
                    + "%d" //birthyear
                    + "\t"
                    + "%s" //breed
                    + lineBreak
                    + "%f" //weight
                    + "\t"
                    + "%s" //owner
                    + "\t"
                    + "%d" //check in month
                    + "\t"
                    + "%d" //check in day
                    + "\t"
                    + "%d" //check in year
                    + "\t"
                    + "%d" //roomNum
                    + lineBreak,
                    g.getName(),
                    g.getBirthDate().getDay(),
                    g.getBirthDate().getMonth(),
                    g.getBirthDate().getYear(),
                    g.getBreed(),
                    g.getWeight(),
                    g.getOwner(),
                    g.getCheckIn().getMonth(),
                    g.getCheckIn().getDay(),
                    g.getCheckIn().getYear(),
                    g.getRoomNumber());

        }
        return data;
    }

//public static void main(String[] args){
//Hotel hotelSalve = new Hotel("Hotel Salve","18 Patrick Dr, Gorham, Me", 99.50, 10);
//Guest testDog = new Guest(9,22,2015, 8,28,1985,"black lab",55.2,"Slow Poke", "Nick Salve");
//Guest testDog2 = new Guest(9,22,2015, 4,17,1989,"spaniel",37.2,"Googly Bear", "Cassandra Salve");
//Guest testDog3 = new Guest(9,22,2015, 3,15,2013,"border collie",25.2,"Phoebe", "Cassandra Salve");
//    
//hotelSalve.addGuest(testDog);
//System.out.println(testDog.getRoomNumber());
//testDog.setRoomNumber(hotelSalve.findEmptyRoom());
//System.out.println(testDog.getRoomNumber());
//    
//hotelSalve.addGuest(testDog2);
//System.out.println(testDog2.getRoomNumber());
//testDog2.setRoomNumber(hotelSalve.findEmptyRoom());
//System.out.println(testDog2.getRoomNumber());
//
//hotelSalve.addGuest(testDog3);
//System.out.println(testDog3.getRoomNumber());
//testDog3.setRoomNumber(hotelSalve.findEmptyRoom());
//System.out.println(testDog3.getRoomNumber());
//
//System.out.println(hotelSalve.saveData());
//}//main
}//End Hotel Class
