package eurobet.src.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TeamServerImpl extends UnicastRemoteObject implements TeamServer {
    private ArrayList<Team> teamList;

    public TeamServerImpl() throws RemoteException {
        super();
        teamList = new ArrayList<>();
    }

    @Override
    public void addTeam(Team team) throws RemoteException {
        teamList.add(team);
    }

    @Override
    public List<Team> getTeams() throws RemoteException {
        return teamList;
    }

}
