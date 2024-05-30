package eurobet.src.main;

import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    //private boolean isFraud;
    private int points = 0;
    private ArrayList<Match> tips = new ArrayList<>();

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        //this.isFraud = isFraud;   // change it
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<Match> getTips() {
        return tips;
    }

    public void addTips(Match tip) {
        this.tips.add(tip);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName +
                ", userName='" + userName +
                ", points=" + points
                ;
    }
}