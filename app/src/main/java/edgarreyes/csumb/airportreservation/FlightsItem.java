package edgarreyes.csumb.airportreservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FlightsItem {

    private UUID flightId;
    private String flightNo;
    private String departure;
    private String arrival;
    private String departureTime;
    private int flightCap;
    private double price;
    private Date date;

    private SimpleDateFormat sfDate = new SimpleDateFormat("dd-MM-yyyy '@' h:mm a");

    public FlightsItem() {
        flightId = UUID.randomUUID();
        date = new Date();
    }

    public FlightsItem(UUID id) {
        flightId = id;
        date = new Date();
    }

    public UUID getFlightId() {
        return flightId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getFlightCap() {
        return flightCap;
    }

    public void setFlightCap(int flightCap) {
        this.flightCap = flightCap;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateString(Date date) {
        return sfDate.format(date);
    }

    public SimpleDateFormat getSfDate() {
        return sfDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=-=-=-=-=-=-=-=\n");
        sb.append(sfDate.format(date)).append("\n");
        sb.append(flightNo).append(":").append(departure).append(":").append(arrival).append(":").append(flightCap).append("\n");
        return sb.toString();
    }
}


