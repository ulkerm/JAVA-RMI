package eurobet.src.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ApplicationController {
    private Button loginBtn;
    private GridPane rootPane;
    private ListView<Match> gamesView;
    private ListView<Bet> betListView;
    private TextField[] fieldForUserCheck;
    private ObservableList<Match> games = FXCollections.observableArrayList();
    private ObservableList<Bet> bets = FXCollections.observableArrayList();
    private RadioButton firstTeam;
    private RadioButton secondTeam;
    private RadioButton draw;
    private Button submitBtn;
    private String firstTeamName;
    private String secondTeamName;
    private ToggleGroup toggleGroup;
    private String location;

    public GridPane createMainApplicationPane() {
        rootPane = new GridPane();
        rootPane.setPadding(new Insets(100));
        rootPane.setHgap(20);
        rootPane.setVgap(20);
        fieldForUserCheck = new TextField[2];
        TextField text1 = new TextField();
        fieldForUserCheck[0] = text1;
        GridPane.setHgrow(text1, Priority.ALWAYS);
        TextField text2 = new TextField();
        fieldForUserCheck[1] = text2;
        GridPane.setHgrow(text2, Priority.ALWAYS);
        loginBtn = new Button("Login");
        loginBtn.setPrefWidth(70);
        GridPane.setHalignment(loginBtn, HPos.CENTER);
        rootPane.add(new Label("Username:"), 0, 0);
        rootPane.add(text1, 1, 0);
        rootPane.add(new Label("Password:"), 0, 1);
        rootPane.add(text2, 1, 1);
        rootPane.add(loginBtn, 0, 2, 2, 1);
        return rootPane;
    }

    public boolean isValidUser() {
        return (fieldForUserCheck[0].getText().equals("mika") && fieldForUserCheck[1].getText().equals("mika"));
    }

    public void showGamesListDialog() {
        gamesView = createGameList();
        gamesView.setItems(GameList.getInstance().getGameList());
        int point = 22;
        betListView = createBetList();
        betListView.setItems(BetList.getInstance().getBetList());
        GridPane grid= displayFutureMatch();
        Label labelForPoint = new Label("Your points:  " + point);
        VBox v = new VBox(labelForPoint, grid);
        v.setSpacing(20);
        v.setPadding(new Insets(10));
        HBox h = new HBox(gamesView, betListView, v);
        h.setSpacing(20);
        h.setPadding(new Insets(10));
        HBox.setHgrow(h, Priority.ALWAYS);
        Scene scene = new Scene(h, 800, 300);
        Stage stage = new Stage();
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
        submitBtn.setOnAction(event -> displayBet());
    }

    private GridPane displayFutureMatch() {
        GridPane grid = new GridPane();
        grid.add(new Label("Bet on the upcoming match:"), 1, 0);
        location = "Italy";
        grid.add(new Label("Location: " + location),1, 1);
        toggleGroup = new ToggleGroup();
        firstTeamName = "Dortmund";
        firstTeam = new RadioButton(firstTeamName);
        firstTeam.setToggleGroup(toggleGroup);
        secondTeamName = "Barcelona";
        secondTeam = new RadioButton(secondTeamName);
        secondTeam.setToggleGroup(toggleGroup);
        draw = new RadioButton("draw");
        draw.setToggleGroup(toggleGroup);
        submitBtn = new Button("Submit");
        grid.add(firstTeam, 1, 2);
        grid.add(secondTeam, 1, 3);
        grid.add(draw, 1, 4);
        grid.add(submitBtn, 1, 7);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        return grid;
    }

    public void displayBet() {
        String selectedTeam = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
        ArrayList<Team> pair = new ArrayList<>();
        pair.add(new Team(firstTeamName));
        pair.add(new Team(secondTeamName));
        LocalDateTime date = LocalDateTime.now();
        String result = selectedTeam.equals(firstTeamName) ? "1 : 0" : (selectedTeam.equals(secondTeamName) ?
                "0 : 1" : "0 : 0");
        Match newMatch = new Match(pair, location, date, result);
        ArrayList<Match> usersBet = new ArrayList<>();
        usersBet.add(newMatch);
        Bet newbet = new Bet(new User("mika", "mika", "mika", "mika"), usersBet);
        BetList.getInstance().addBet(newbet);
        if (betListView != null) {
            betListView.setItems(BetList.getInstance().getBetList());
        } else {
            System.out.println("betListView is not initialized.");
        }
    }

    // this methode is for testing
    private void createMatch() {
        Team t1 = new Team("Napoli");
        Team t2 = new Team("Barcelona");
        ArrayList<Team> pair =new ArrayList<>();
        pair.add(t1);
        pair.add(t2);
        LocalDateTime date = LocalDateTime.of(2023, 6, 15, 18, 30);
        Match m1 = new Match(pair, "Spain",date ,"1 : 1");
        GameList.getInstance().addMatch(m1);
    }

    private ListView<Match> createGameList() {
        createMatch();
        ListView<Match> temp = new ListView<>();
        temp.setPrefHeight(190);
        temp.setFixedCellSize(25);
        temp.setItems(games);
        temp.scrollTo(games.size());
        return temp;
    }

    private ListView<Bet> createBetList() {
        ListView<Bet> temp = new ListView<>();
        temp.setPrefHeight(190);
        temp.setFixedCellSize(25);
        temp.setItems(bets);
        temp.scrollTo(bets.size());
        return temp;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public Button getSubmitBtn() {
        return submitBtn;
    }
}
