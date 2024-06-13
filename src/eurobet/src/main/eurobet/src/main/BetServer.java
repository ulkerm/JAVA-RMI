package eurobet.src.main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BetServer extends Remote {
    void addBet(Bet bet) throws RemoteException;
    List<Bet> getBets() throws RemoteException;

}
