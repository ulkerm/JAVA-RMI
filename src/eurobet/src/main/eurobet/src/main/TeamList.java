package eurobet.src.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TeamList {
    private static TeamList instance = new TeamList();
    private static String filename = "UserTeamItems.txt";

    private ObservableList<Team> teamList;

    public static TeamList getInstance() {
        return instance;
    }

    private TeamList() {
        teamList = FXCollections.observableArrayList(); // Initialize userList here
    }

    public ObservableList<Team> getTeamList() {
        return teamList;
    }

    public void addTeam(Team newTeam) {
        if(!teamList.contains(newTeam))
            teamList.add(newTeam);
    }

    public void deleteTeam(Team newTeam) {
        if(teamList.contains(newTeam)) {
            teamList.remove(newTeam);
        }

    }


    public void loadTeamList() throws IOException {
        teamList = FXCollections.observableArrayList(); // Reinitialize teamList to ensure it is empty before loading
        Path path = Paths.get(filename);

        if (Files.exists(path)) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                String input;
                while ((input = br.readLine()) != null) {
                    String[] teams = input.split("\t");
                    String teamName = teams[0];
                    Team team = new Team(teamName);
                    teamList.add(team);
                }
            } catch (IOException e) {
                System.err.println("Error reading team list from file: " + e.getMessage());
                throw e;
            }
        } else {
            System.err.println("Team list file does not exist: " + filename);
        }
    }

    public void storeTeamList() throws IOException {
        Path path = Paths.get(filename);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Team team : teamList) {
                bw.write(team.getName());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing team list to file: " + e.getMessage());
            throw e;
        }
    }
}
