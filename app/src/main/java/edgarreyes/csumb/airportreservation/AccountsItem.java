package edgarreyes.csumb.airportreservation;

import java.util.UUID;

public class AccountsItem {

    private UUID accId;
    private String type;
    private String username;
    private String password;

    public AccountsItem() {
        accId = UUID.randomUUID();
    }

    public AccountsItem(UUID Id) {
        accId = Id;
    }

    public UUID getAccountsId() {
        return accId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=-=-=-=-=-=-=-=\n");
        sb.append(type).append(":").append(username).append(":").append(password).append("\n");
        return sb.toString();
    }
}
