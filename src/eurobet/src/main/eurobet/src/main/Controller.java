package eurobet.src.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {
    private Button gameBtn;
    private Button userBtn;
    private Button resultBtn;
    private Button blockBtn;
    private Button modifyBtn;
    private Button createUserBtn;
    private Button addTeamBtn;
    private HBox rootPane;
    ListView<Match> gamesList;
    ListView<User> messageList;
    ListView<Team> teamList;

    private TextField[] fields;
    private DatePicker date;
    private Spinner<Integer> hourSpinner;
    Spinner<Integer> minuteSpinner;

    private ObservableList<Match> games = FXCollections.observableArrayList();
    private ObservableList<User> userslist = FXCollections.observableArrayList();
    private ObservableList<Team> teams = FXCollections.observableArrayList();

    public HBox createMainPane() {
        gamesList = createGameList();
        messageList = createMessageList(userslist);
        teamList = createTeamList(teams);
        teamList.setItems(TeamList.getInstance().getTeamList());
        teamList.scrollTo(TeamList.getInstance().getTeamList().size());
        gamesList.setItems(GameList.getInstance().getGameList());
        gamesList.scrollTo(GameList.getInstance().getGameList().size());
        messageList.setItems(UserList.getInstance().getUserList());
        messageList.scrollTo(UserList.getInstance().getUserList().size());
        gameBtn = new Button("Create a new game");
        createUserBtn = new Button("Create new user");
        resultBtn = new Button("Set result");
        addTeamBtn = new Button("Add Team");
        VBox t = new VBox(teamList, addTeamBtn);
        t.setSpacing(10);
        HBox gH = new HBox(gameBtn, resultBtn);
        gH.setSpacing(5);
        VBox g = new VBox(gamesList, gH);
        g.setSpacing(10);
        blockBtn = new Button("Block");
        modifyBtn = new Button("Modify");
        HBox uH = new HBox(createUserBtn, blockBtn, modifyBtn);
        uH.setSpacing(5);
        VBox u = new VBox(messageList, uH);
        u.setSpacing(10);

        rootPane = new HBox();
        rootPane.setPadding(new Insets(10));
        rootPane.setSpacing(10);
        rootPane.setAlignment(Pos.BASELINE_CENTER);
        rootPane.setHgrow(g, Priority.ALWAYS);
        rootPane.setHgrow(u, Priority.ALWAYS);
        rootPane.getChildren().addAll(t, g, u);

        return rootPane;
    }
    private ListView<Match> createGameList() {
        ListView<Match> messageList = new ListView<>();
        messageList.setPrefHeight(190);
        messageList.setFixedCellSize(25);
        HBox.setHgrow(messageList, Priority.ALWAYS);
        messageList.setItems(games);
        return messageList;
    }

    private ListView<User> createMessageList(ObservableList<User> messages) {
        ListView<User> messageList = new ListView<>();
        messageList.setPrefHeight(190);
        messageList.setFixedCellSize(25);
        HBox.setHgrow(messageList, Priority.ALWAYS);
        messageList.setItems(messages);
        return messageList;
    }

    private ListView<Team> createTeamList(ObservableList<Team> list) {
        ListView<Team> teamList = new ListView<>();
        teamList.setPrefHeight(190);
        teamList.setFixedCellSize(25);
        HBox.setHgrow(teamList, Priority.ALWAYS);
        teamList.setItems(list);
        return teamList;
    }

    public GridPane createNewMatch() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        fields = new TextField[4];
        TextField text1 = new TextField();
        GridPane.setHgrow(text1, Priority.ALWAYS);
        fields[0] = text1;
        TextField text2 = new TextField();
        GridPane.setHgrow(text2, Priority.ALWAYS);
        fields[1] = text2;
        TextField text3 = new TextField();
        GridPane.setHgrow(text3, Priority.ALWAYS);
        fields[2] = text3;
        date = new DatePicker();
        hourSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, LocalTime.now().getHour());
        hourSpinner.setValueFactory(hourFactory);

        minuteSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, LocalTime.now().getMinute());
        minuteSpinner.setValueFactory(minuteFactory);
        GridPane.setHgrow(date, Priority.ALWAYS);
        TextField text4 = new TextField();
        GridPane.setHgrow(text4, Priority.ALWAYS);
        fields[3] = text4;
        grid.add(new Label("First team:"), 0, 0);
        grid.add(text1, 1, 0);
        grid.add(new Label("Second team:"), 0, 1);
        grid.add(text2, 1, 1);
        grid.add(new Label("Location:"), 0, 2);
        grid.add(text3, 1, 2);
        grid.add(new Label("Date:"), 0, 3);
        grid.add(date, 1, 3);
        grid.add(new Label("Hour:"), 0, 4);
        grid.add(hourSpinner, 1, 4);
        grid.add(new Label("Minute:"), 0, 5);
        grid.add(minuteSpinner, 1, 5);
        grid.add(new Label("Result:"), 0, 6);
        grid.add(text4, 1, 6);
        return grid;
    }

    public void showCreateNewMatchDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(rootPane.getScene().getWindow());
        dialog.setTitle("Add New Match");
        dialog.setHeaderText("Use this dialog to create a new match");
        GridPane grid = createNewMatch();
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String firstTeam = fields[0].getText().trim();
            String secondTeam = fields[1].getText().trim();
            String location = fields[2].getText().trim();
            int hour = hourSpinner.getValue();
            int minute = minuteSpinner.getValue();
            LocalDateTime matchDate = LocalDateTime.of(date.getValue(), LocalTime.of(hour, minute));
            String resultOfTheGame = fields[3].getText().trim();

            Team first = new Team(firstTeam);
            Team second = new Team(secondTeam);
            ArrayList<Team> pair = new ArrayList<>();
            pair.add(first);
            pair.add(second);
            Match newMatch = new Match(pair, location, matchDate, resultOfTheGame);
            GameList.getInstance().addMatch(newMatch);
            gamesList.setItems(GameList.getInstance().getGameList());
        }
    }

    private GridPane createSetResultWindow(Match newMatch) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        fields = new TextField[4];
        TextField text1 = new TextField(newMatch.getResult());
        GridPane.setHgrow(text1, Priority.ALWAYS);
        fields[0] = text1;
        grid.add(new Label("Result:"), 0, 0);
        grid.add(text1, 1, 0);
        return grid;
    }

    public void showSetResultDialog() {
        Match newMatch = gamesList.getSelectionModel().selectedItemProperty().get();
        if (newMatch == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Match Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a match before setting the result.");
            alert.showAndWait();
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(rootPane.getScene().getWindow());
        dialog.setTitle("Set result");
        dialog.setHeaderText("Use this dialog to set the result of the match");
        GridPane grid = createSetResultWindow(newMatch);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            newMatch.setResult(fields[0].getText().trim());
            gamesList.setItems(GameList.getInstance().getGameList());
        }
        gamesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        gamesList.getSelectionModel().selectFirst();
    }

    private GridPane createUserWindow() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        fields = new TextField[4];
        TextField text1 = new TextField();
        GridPane.setHgrow(text1, Priority.ALWAYS);
        fields[0] = text1;
        TextField text2 = new TextField();
        GridPane.setHgrow(text2, Priority.ALWAYS);
        fields[1] = text2;
        TextField text3 = new TextField();
        GridPane.setHgrow(text3, Priority.ALWAYS);
        fields[2] = text3;
        TextField text4 = new TextField();
        GridPane.setHgrow(text4, Priority.ALWAYS);
        fields[3] = text4;
        grid.add(new Label("Firstname:"), 0, 0);
        grid.add(text1, 1, 0);
        grid.add(new Label("Lastname:"), 0, 1);
        grid.add(text2, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(text3, 1, 2);
        grid.add(new Label("Username:"), 0, 3);
        grid.add(text4, 1, 3);
        return grid;
    }

    public void showUserDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(rootPane.getScene().getWindow());
        dialog.setTitle("New user");
        dialog.setHeaderText("Use this dialog to create a new user");
        GridPane grid = createUserWindow();
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            processResults();
        }
    }

    private GridPane createModifyWindow(User newUser) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        fields = new TextField[4];
        TextField text1 = new TextField(newUser.getFirstName());
        GridPane.setHgrow(text1, Priority.ALWAYS);
        fields[0] = text1;

        TextField text2 = new TextField(newUser.getLastName());
        GridPane.setHgrow(text2, Priority.ALWAYS);
        fields[1] = text2;

        TextField text3 = new TextField(newUser.getPassword());
        GridPane.setHgrow(text3, Priority.ALWAYS);
        fields[2] = text3;

        TextField text4 = new TextField(newUser.getUserName());
        GridPane.setHgrow(text4, Priority.ALWAYS);
        fields[3] = text4;

        grid.add(new Label("Firstname:"), 0, 0);
        grid.add(text1, 1, 0);
        grid.add(new Label("Lastname:"), 0, 1);
        grid.add(text2, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(text3, 1, 2);
        grid.add(new Label("Username:"), 0, 3);
        grid.add(text4, 1, 3);

        return grid;
    }

    public void showUserModifyDialog() {
        User newUser = messageList.getSelectionModel().selectedItemProperty().get();
        if (newUser == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No user Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a user.");
            alert.showAndWait();
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(rootPane.getScene().getWindow());
        dialog.setTitle("Modify user");
        dialog.setHeaderText("Use this dialog to modify the user data");
        GridPane grid = createModifyWindow(newUser);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            newUser.setUserName(fields[0].getText().trim());
            newUser.setLastName(fields[1].getText().trim());
            newUser.setPassword(fields[2].getText().trim());
            newUser.setUserName(fields[3].getText().trim());
            messageList.setItems(UserList.getInstance().getUserList());
        }
        messageList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        messageList.getSelectionModel().selectFirst();
    }

    private User create() {
        String firstname = fields[0].getText().trim();
        String lastname = fields[1].getText().trim();
        String password = fields[2].getText().trim();
        String username = fields[3].getText().trim();

        User newUser = new User(firstname, lastname, password, username);
        return newUser;
    }

    public void processResults() {
        User newUser = create();
        UserList.getInstance().addUser(newUser);
        messageList.setItems(UserList.getInstance().getUserList()); // Refresh the user list
    }

    public void blockUser() {
        User newUser = messageList.getSelectionModel().selectedItemProperty().get();
        UserList.getInstance().deleteUser(newUser);
        messageList.setItems(UserList.getInstance().getUserList());
        messageList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        messageList.getSelectionModel().selectFirst();
    }

    private GridPane createNewTeamWindow() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        fields = new TextField[1];
        TextField text1 = new TextField();
        GridPane.setHgrow(text1, Priority.ALWAYS);
        fields[0] = text1;
        grid.add(new Label("Name:"), 0, 0);
        grid.add(text1, 1, 0);
        return grid;
    }

    public void showNewTeamDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(rootPane.getScene().getWindow());
        dialog.setTitle("Add Teams");
        dialog.setHeaderText("Use this dialog to add a new team");
        GridPane grid = createNewTeamWindow();
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Team newTeam = new Team(fields[0].getText().trim());
            TeamList.getInstance().addTeam(newTeam);
            teamList.setItems(TeamList.getInstance().getTeamList());
        }
    }

    public Button getGameBtn() {
        return gameBtn;
    }

    public Button getAddTeamBtn() {
        return addTeamBtn;
    }

    public Button getUserBtn() {
        return userBtn;
    }

    public Button getResultBtn() {
        return resultBtn;
    }

    public Button getBlockBtn() {
        return blockBtn;
    }

    public Button getModifyBtn() {
        return modifyBtn;
    }

    public Button getCreateUserBtn() {
        return createUserBtn;
    }

}
