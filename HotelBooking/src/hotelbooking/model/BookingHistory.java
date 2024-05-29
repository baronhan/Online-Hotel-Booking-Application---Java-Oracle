
package hotelbooking.model;

import java.io.Serializable;
import java.util.Date;


public class BookingHistory implements Serializable{
    private String bookingId;
    private String userId;
    private String roomName;
    private int noOfPeople;
    private String roomType;
    private String serviceName;
    private float servicePrice;
    private float roomPrice;
    private float total;
    private Date checkIn;
    private Date checkOut;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public BookingHistory() {
    }

    public BookingHistory(String bookingId, String userId, String roomName, int noOfPeople, String roomType, String serviceName, float servicePrice, float roomPrice, float total, Date checkIn, Date checkOut) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomName = roomName;
        this.noOfPeople = noOfPeople;
        this.roomType = roomType;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.roomPrice = roomPrice;
        this.total = total;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
   

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServicePrice(float servicePrice) {
        this.servicePrice = servicePrice;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getNoOfPeople() {
        return noOfPeople;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public float getServicePrice() {
        return servicePrice;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public float getTotal() {
        return total;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    
    
    
}
