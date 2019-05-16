package edgarreyes.csumb.airportreservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class AccountsItem {

    private UUID accId;
    private String transactionType;
    private String type;
    private String username;
    private String password;
    private Date date;
    private HashMap<FlightsItem, Integer> flights = new HashMap<>();

    private SimpleDateFormat sfDate = new SimpleDateFormat("dd-MM-yyyy '@' h:mm a");


    public AccountsItem() {
        accId = UUID.randomUUID();
        date = new Date();
    }

    public AccountsItem(UUID Id) {
        accId = Id;
        date = new Date();
    }

    public UUID getAccountsId() {
        return accId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public HashMap<FlightsItem, Integer> getFlights() {
        return flights;
    }

    public void setFlights(HashMap<FlightsItem, Integer> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=-=-=-=-=-=-=-=\n");
        sb.append(sfDate.format(date)).append("\n");
        sb.append(type).append(":").append(username);
        if(!flights.isEmpty()){
            for(FlightsItem i : flights.keySet()){
                Integer tickets = flights.get(i);
                sb.append(i.getFlightNo()).append(":").append(i.getDeparture()).append(":").append(i.getArrival()).append((i.getPrice()* tickets));
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
