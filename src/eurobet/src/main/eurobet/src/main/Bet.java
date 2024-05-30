package eurobet.src.main;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Bet {
    private User user;
    private ArrayList<Match> bets;

    public Bet(User user, ArrayList<Match> bets) {
        this.user = user;
        this.bets = bets;
    }

    private boolean isPossibleTip(Match match) {
        LocalDateTime curTime = LocalDateTime.now();
        LocalDateTime matchTime = match.getDate();
        return curTime.isBefore(matchTime.plusMinutes(95));
    }

    public void addTip(Match match, Match bet) {
        if(isPossibleTip(match))
            bets.add(bet);
    }

    @Override
    public String toString() {
        return "Bet{" +
                "bets=" + bets +
                '}';
    }

    public int getUserPoint() {
        return user.getPoints();
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
}
