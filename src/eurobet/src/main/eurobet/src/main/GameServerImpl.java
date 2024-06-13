package eurobet.src.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class GameServerImpl extends UnicastRemoteObject implements GameServer {
    private ArrayList<Match> gameList;

    public GameServerImpl() throws RemoteException {
        super();
        gameList = new ArrayList<>();
    }

    @Override
    public void addGame(Match match) throws RemoteException {
        gameList.add(match);
    }

    @Override
    public List<Match> getGames() throws RemoteException {
        return gameList;
    }

    @Override
    public void updateGame(Match match) throws RemoteException {
        for (int i = 0; i < gameList.size(); i++) {
            if (gameList.get(i).getMatchLocation().equals(match.getMatchLocation()) && gameList.get(i).getDate().equals(match.getDate())) {
                gameList.set(i, match);
                break;
            }
        }
    }
}
