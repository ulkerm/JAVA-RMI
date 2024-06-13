package eurobet.src.main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GameServer extends Remote {
    void addGame(Match match) throws RemoteException;
    List<Match> getGames() throws RemoteException;
    void updateGame(Match match) throws RemoteException;
}
