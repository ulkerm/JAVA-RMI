package eurobet.src.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class BettingApplication extends Application {

    private ApplicationController applicationController = new ApplicationController();


    public static void main(String[] args) {
        launch(args);
    }

    public class ButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                BettingApplication.this.handleButtonEvent(event);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleButtonEvent(ActionEvent event) throws RemoteException {
        if (event.getTarget() instanceof Button) {
            Button button = (Button) event.getTarget();
            if(button == applicationController.getLoginBtn() && applicationController.isValidUser())
                applicationController.showGamesListDialog();

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            GameServer gameServer = (GameServer) Naming.lookup("rmi://localhost/GameServer");
            applicationController.setGameServer(gameServer);
            BetServer betServer = (BetServer) Naming.lookup("rmi://localhost/BetServer");
            applicationController.setBetServer(betServer);
            UserServer userServer = (UserServer) Naming.lookup("rmi://localhost/UserServer");
            applicationController.setUserServer(userServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GridPane rootPane = applicationController.createMainApplicationPane();
        EventHandler<ActionEvent> handler = new ButtonEventHandler();
        applicationController.getLoginBtn().setOnAction(handler);

        Scene scene = new Scene(rootPane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(340);
        primaryStage.setMinHeight(260);
        primaryStage.setTitle("Betting Application");
        primaryStage.show();
    }
}
