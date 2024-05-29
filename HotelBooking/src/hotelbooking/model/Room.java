package hotelbooking.model;

import java.io.Serializable;

public class Room implements Serializable{
    private String roomID;
    private String roomName;
    private int noPeople;
    private float price;
    private String roomDesciption;
    private String roomType;
    private String roomPicture;
    private boolean roomStatus;

    public Room() {
    }

    public Room(String roomID, String roomName, int noPeople, float price, String roomDesciption, String roomType, String roomPicture, boolean roomStatus) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.noPeople = noPeople;
        this.price = price;
        this.roomDesciption = roomDesciption;
        this.roomType = roomType;
        this.roomPicture = roomPicture;
        this.roomStatus = roomStatus;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setNoPeople(int noPeople) {
        this.noPeople = noPeople;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRoomDesciption(String roomDesciption) {
        this.roomDesciption = roomDesciption;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomPicture(String roomPicture) {
        this.roomPicture = roomPicture;
    }

    public void setRoomStatus(boolean roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getNoPeople() {
        return noPeople;
    }

    public float getPrice() {
        return price;
    }

    public String getRoomDesciption() {
        return roomDesciption;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomPicture() {
        return roomPicture;
    }

    public boolean isRoomStatus() {
        return roomStatus;
    }
    
    
}
