package eurobet.src.main;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Match implements Serializable {

    private ArrayList<Team> teams;
    private String matchLocation;
    private String date;
    private String result;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Match(ArrayList<Team> teams, String matchLocation, LocalDateTime date, String result) {
        this.teams = teams;
        this.matchLocation = matchLocation;
        this.date = date.format(formatter);
        this.result = result;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public String getMatchLocation() {
        return matchLocation;
    }

    public LocalDateTime getDate() {
        return LocalDateTime.parse(date, formatter);
    }

    public String getResult() {
        return result;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void setResult(String result) {
        this.result = result;
    }



    @Override
    public String toString() {
        return teams +
                ", Location='" + matchLocation + '\'' +
                ", date=" + date +
                ", result='" + result + '\'' +
                '}';
    }
}
