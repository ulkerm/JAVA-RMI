package eurobet.src.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class UserServerImpl extends UnicastRemoteObject implements UserServer {

    private ArrayList<User> userList;

    public UserServerImpl() throws RemoteException {
        super();
        userList = new ArrayList<>();
    }
    @Override
    public void addUser(User user) throws RemoteException {
        if(!userList.contains(user))
            userList.add(user);
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        return userList;
    }

    @Override
    public void deleteUser(User user) throws RemoteException {
        if(userList.contains(user)) {
            userList.remove(user);
        }
    }

    @Override
    public User isValidUser(String username, String password) throws RemoteException {
        for(User u : userList) {
            return (u.getUserName().equals(username) && u.getPassword().equals(password) ? u : null);
        }
        return null;
    }

    @Override
    public void modifyUser(ArrayList<User> newList) throws RemoteException {
        for (int i = 0; i < newList.size(); i++) {
            userList.set(i, newList.get(i));
        }
    }

}
