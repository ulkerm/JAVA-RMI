package eurobet.src.main;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Bet implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user;
    private ArrayList<Match> bets;

    public ArrayList<Match> getBets() {
        return bets;
    }

    public Bet(User user, ArrayList<Match> bets) {
        this.user = user;
        this.bets = bets;
    }

    private boolean isPossibleTip(Match match) {
        LocalDateTime curTime = LocalDateTime.now();
        LocalDateTime matchTime = match.getDate();
        return curTime.isBefore(matchTime.plusMinutes(10));
    }

    public void addTip(Match match, Match bet) {
        if (match == null || bet == null) {
            throw new IllegalArgumentException("Match and bet must not be null");
        }
        if(isPossibleTip(match))
            bets.add(bet);
    }

    @Override
    public String toString() {
        return "Bet{" +
                bets +
                '}';
    }

    private boolean hasGameStarted(Match match) {
        LocalDateTime tipDate = bets.get(bets.size() -1).getDate();
        LocalDateTime matchTime = match.getDate();
        return tipDate.isBefore(matchTime.plusMinutes(105));
    }

    public void addUserPoints(Match match) {
        if(hasGameStarted(match) && match.getResult().equals(bets.get(bets.size() - 1).getResult()))
            user.setPoints(user.getPoints() + 5);
        else if(isPossibleTip(match) && match.getResult().equals(bets.get(bets.size() - 1).getResult()))
            user.setPoints(user.getPoints() + 2);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
