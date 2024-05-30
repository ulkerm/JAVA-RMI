package eurobet.src.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class GameList {
    private static GameList instance = new GameList();
    private static String filename = "Games.txt";

    private ObservableList<Match> gameList;

    private DateTimeFormatter formatter;

    private GameList() {
        gameList = FXCollections.observableArrayList();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public static GameList getInstance() {
        return instance;
    }

    public ObservableList<Match> getGameList() {
        return gameList;
    }

    public void addMatch(Match match) {
        gameList.add(match);
    }

    public void deleteMatch(Match match) {
        if(gameList.contains(match)) {
            gameList.remove(match);
        }
    }


    public void loadGameList() throws IOException {
        gameList = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] m = input.split("\t");
                String firstTeam = m[0];
                String secondTeam = m[1];
                String loaction = m[2];
                String dateString = m[3];
                String result = m[4];
                Team t1 = new Team(firstTeam);
                Team t2 = new Team(secondTeam);
                ArrayList<Team> pair = new ArrayList<>();
                pair.add(t1);
                pair.add(t2);
                LocalDateTime date = LocalDateTime.parse(dateString, formatter);

                Match newMatch = new Match(pair, loaction, date, result);
                gameList.add(newMatch);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void storeGameList() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Match> iter = gameList.iterator();
            while (iter.hasNext()) {
                Match match = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s\t%s",
                        match.getTeams(),
                        match.getMatchLocation(),
                        match.getResult(),
                        match.getDate().format(formatter)));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
}
