
package hotelbooking.model;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable{
    private String bookingId;
    private String userId;
    private String roomId;
    private String serviceId;
    private Date checkIn;
    private Date checkOut;
    private String creditNumber;
    private float total;

    public Booking() {
    }

    public Booking(String bookingId, String userId, String roomId, String serviceId, Date checkIn, Date checkOut, String creditNumber, float total) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomId = roomId;
        this.serviceId = serviceId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.creditNumber = creditNumber;
        this.total = total;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public float getTotal() {
        return total;
    }
    
    
}
