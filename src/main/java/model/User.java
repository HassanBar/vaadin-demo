package model;

public class User {
    private String phonenumber;
    private String uuid;

    public User(String phonenumber, String uuid) {
        this.phonenumber = phonenumber;
        this.uuid = uuid;
    }
    public User() {}

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
