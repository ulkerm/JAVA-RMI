package eurobet.src.main;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ApplicationController {
    private Button loginBtn;
    private GridPane rootPane;
    private ListView<Match> gamesList;
    private ListView<Bet> betListView;
    private TextField[] fieldForUserCheck;
    private RadioButton firstTeam;
    private RadioButton secondTeam;
    private RadioButton draw;
    private Button submitBtn;
    private String firstTeamName;
    private String secondTeamName;
    private ToggleGroup toggleGroup;
    private String location;
    private BetServer betServer;
    private UserServer userServer;
    private User curUser;
    private GameServer gameServer;

    public void setGameServer(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    public void setBetServer(BetServer betServer) {
        this.betServer = betServer;
    }

    public void setUserServer(UserServer userServer) {
        this.userServer = userServer;
    }

    private ListView<Bet> createBetList() throws RemoteException {
        ListView<Bet> temp = new ListView<>();
        temp.setPrefHeight(190);
        temp.setFixedCellSize(25);
        temp.setItems(FXCollections.observableArrayList(betServer.getBets()));
        temp.scrollTo(temp.getItems().size());
        return temp;
    }

    private ListView<Match> createGameList() throws RemoteException {
        ListView<Match> temp = new ListView<>();
        temp.setPrefHeight(190);
        temp.setFixedCellSize(25);
        temp.setItems(FXCollections.observableArrayList(gameServer.getGames()));
        temp.scrollTo(temp.getItems().size());
        return temp;
    }

    public GridPane createMainApplicationPane() {
        rootPane = new GridPane();
        rootPane.setPadding(new Insets(100));
        rootPane.setHgap(20);
        rootPane.setVgap(20);
        fieldForUserCheck = new TextField[2];
        TextField text1 = new TextField();
        fieldForUserCheck[0] = text1;
        GridPane.setHgrow(text1, Priority.ALWAYS);
        TextField text2 = new PasswordField();
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
        try {
            curUser = userServer.isValidUser(fieldForUserCheck[0].getText(), fieldForUserCheck[1].getText());
            return curUser != null ? true : false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showGamesListDialog() throws RemoteException {
        try {
            gamesList = createGameList();
            gamesList.getItems().setAll(gameServer.getGames());
            betListView = createBetList();
            betListView.getItems().setAll(betServer.getBets());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        int point = curUser.getPoints();
        GridPane grid= displayFutureMatch();
        Label labelForPoint = new Label("Your points:  " + point);
        VBox v = new VBox(labelForPoint, grid);
        v.setSpacing(20);
        v.setPadding(new Insets(10));
        HBox h = new HBox(gamesList, betListView, v);
        h.setSpacing(20);
        h.setPadding(new Insets(10));
        HBox.setHgrow(h, Priority.ALWAYS);
        Scene scene = new Scene(h, 800, 300);
        Stage stage = new Stage();
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
        submitBtn.setOnAction(event -> {
            try {
                displayBet();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Match onComingMatch() throws RemoteException {
        for(int i = 0; i < gameServer.getGames().size(); i++) {
            if(gameServer.getGames().get(i).getDate().isAfter(LocalDateTime.now().minusMinutes(95)))
                return gameServer.getGames().get(i);
        }
        return null;
    }

    private GridPane displayFutureMatch() throws RemoteException {
        GridPane grid = new GridPane();
        Match matchForBet = onComingMatch() != null ? onComingMatch() : null;
        grid.add(new Label("Bet on the upcoming match:"), 1, 0);
        location = matchForBet == null ? "" : matchForBet.getMatchLocation();
        grid.add(new Label("Location: " + location),1, 1);
        toggleGroup = new ToggleGroup();
        firstTeamName = matchForBet == null ? "" : matchForBet.getTeams().get(0).getName();
        firstTeam = new RadioButton(firstTeamName);
        firstTeam.setToggleGroup(toggleGroup);
        secondTeamName = matchForBet == null ? "" : matchForBet.getTeams().get(1).getName();
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

    public void displayBet() throws RemoteException {
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
        Bet newbet = new Bet(curUser, usersBet);
        Match matchForBet = onComingMatch() != null ? onComingMatch() : null;
        newbet.addTip(matchForBet, newMatch);
        betServer.addBet(newbet);
        betListView.getItems().add(newbet);
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

}
