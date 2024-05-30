package eurobet.src.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserList {
    private static UserList instance = new UserList();
    private static String filename = "UserListItems.txt";

    private ObservableList<User> userList;

    public static UserList getInstance() {
        return instance;
    }

    private UserList() {
        userList = FXCollections.observableArrayList();
    }

    public ObservableList<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        if(!userList.contains(user))
            userList.add(user);
    }

    public void deleteUser(User user) {
        if(userList.contains(user)) {
            userList.remove(user);
        }

    }

public void loadUserList() throws IOException {
    userList = FXCollections.observableArrayList();
    Path path = Paths.get(filename);
    if (Files.exists(path)) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String input;
            while ((input = br.readLine()) != null) {
                String[] users = input.split("\t");

                String userFirstname = users[0];
                String userLastname = users[1];
                String userPassword = users[2];
                String userUsername = users[3];

                User us = new User(userFirstname, userLastname, userPassword, userUsername);
                userList.add(us);
            }
        } catch (IOException e) {
            System.err.println("Error reading user list from file: " + e.getMessage());
            throw e;
        }
    } else {
        System.err.println("User list file does not exist: " + filename);
    }
}

    public void storeUserList() throws IOException {
        Path path = Paths.get(filename);
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (User user : userList) {
                bw.write(String.join("\t", user.getFirstName(), user.getLastName(), user.getPassword(), user.getUserName()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing user list to file: " + e.getMessage());
            throw e;
        }
    }
}
