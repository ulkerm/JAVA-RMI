package eurobet.src.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BetServerImpl extends UnicastRemoteObject implements BetServer {
    private static final Logger logger = Logger.getLogger(BetServerImpl.class.getName());
    private ArrayList<Bet> betList;

    public BetServerImpl() throws RemoteException {
        super();
        betList = new ArrayList<>();
    }

    @Override
    public void addBet(Bet bet) throws RemoteException {
        logger.log(Level.INFO, "Adding bet: " + bet);
        betList.add(bet);
    }

    @Override
    public List<Bet> getBets() throws RemoteException {
        logger.log(Level.INFO, "Retrieving bet list");
        return betList;
    }

}
