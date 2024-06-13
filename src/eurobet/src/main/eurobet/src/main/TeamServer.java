package eurobet.src.main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TeamServer extends Remote {
    void addTeam(Team team) throws RemoteException;
    List<Team> getTeams() throws RemoteException;

}
