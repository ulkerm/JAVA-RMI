package eurobet.src.main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface UserServer extends Remote {
    void addUser(User user) throws RemoteException;

    List<User> getUsers() throws RemoteException;
    void deleteUser(User user) throws RemoteException;
    User isValidUser(String username, String password) throws RemoteException;
    void modifyUser(ArrayList<User> newList) throws RemoteException;
}

