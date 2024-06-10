package eurobet.src.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Match {

    private ArrayList<Team> teams;
    private String matchLocation;
    private LocalDateTime date;
    private String result;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Match(ArrayList<Team> teams, String matchLocation, LocalDateTime date, String result) {
        this.teams = teams;
        this.matchLocation = matchLocation;
        this.date = date;
        this.result = result;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public String getMatchLocation() {
        return matchLocation;
    }

    public LocalDateTime getDate() {
        return date;
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
                ", date=" + date.format(formatter) +
                ", result='" + result + '\'' +
                '}';
    }
}
