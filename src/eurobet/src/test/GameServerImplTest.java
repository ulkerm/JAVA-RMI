import eurobet.src.main.GameServerImpl;
import eurobet.src.main.Match;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServerImplTest {
    private GameServerImpl server;
    private Registry registry;

    @BeforeEach
    public void setUp() throws Exception {
        try {
            registry = LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            registry = LocateRegistry.getRegistry(1099);
        }

        server = new GameServerImpl();
        Naming.rebind("rmi://localhost/GameServer", server);
    }

    @AfterEach
    public void tearDown() throws Exception {
        Naming.unbind("rmi://localhost/GameServer");
        UnicastRemoteObject.unexportObject(server, true);
    }

    @Test
    public void testAddGame() throws RemoteException {
        Match match2 = new Match(new ArrayList<>(), "Italy", LocalDateTime.now().plusDays(1), "1 : 0");
        server.addGame(match2);
        assertEquals(1, server.getGames().size());
    }

    @Test
    public void testGetGames() throws RemoteException {
        Match match1 = new Match(new ArrayList<>(), "Italy", LocalDateTime.now().plusDays(1), "1 : 0");
        server.addGame(match1);

        List<Match> games = server.getGames();
        assertEquals(1, games.size());
        assertEquals(match1, games.get(0));
    }

    @Test
    public void testAddMultipleGames() throws RemoteException {
        Match match1 = new Match(new ArrayList<>(), "Italy", LocalDateTime.now().plusDays(1), "1 : 0");
        Match match2 = new Match(new ArrayList<>(), "Spain", LocalDateTime.now().plusDays(2), "2 : 1");

        server.addGame(match1);
        server.addGame(match2);

        assertEquals(2, server.getGames().size());
    }

}
