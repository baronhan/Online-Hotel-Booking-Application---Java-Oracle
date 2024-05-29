
package hotelbooking.model;

import java.io.Serializable;

public class Service implements Serializable{
    private String serviceID;
    private String serviceName;
    private String acronym;
    private float price;
    private String serviceType;

    public Service() {
    }

    public Service(String serviceID, String serviceName, String acronym, float price, String serviceType) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.acronym = acronym;
        this.price = price;
        this.serviceType = serviceType;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getAcronym() {
        return acronym;
    }

    public float getPrice() {
        return price;
    }

    public String getServiceType() {
        return serviceType;
    }
    
    
}
