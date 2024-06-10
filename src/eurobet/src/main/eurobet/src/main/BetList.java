package eurobet.src.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BetList {
    private static BetList instance = new BetList();
    private static String filename = "BetList.txt";

    private ObservableList<Bet> betList;

    public static BetList getInstance() {
        return instance;
    }

    private BetList() {
        betList = FXCollections.observableArrayList(); // Initialize userList here
    }

    public ObservableList<Bet> getBetList() {
        return betList;
    }

    public void addBet(Bet newBet) {
        betList.add(newBet);
    }

}
