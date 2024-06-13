package eurobet.src.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Management extends Application {

    private Controller controller = new Controller();

    public static void main(String[] args) {
        launch(args);
    }

    public class ButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                Management.this.handleButtonEvent(event);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleButtonEvent(ActionEvent event) throws RemoteException {
        if (event.getTarget() instanceof Button) {
            Button button = (Button) event.getTarget();
            if(button == controller.getGameBtn())
                controller.showCreateNewMatchDialog();
            else if(button == controller.getCreateUserBtn())
                controller.showUserDialog();
            else if(button == controller.getModifyBtn())
                controller.showUserModifyDialog();
            else if(button == controller.getBlockBtn())
                controller.blockUser();
            else if (button == controller.getAddTeamBtn())
                controller.showNewTeamDialog();
            else if (button == controller.getGameBtn())
                controller.showCreateNewMatchDialog();
            else if (button == controller.getResultBtn())
                controller.showSetResultDialog();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            UserServer userServer = (UserServer) Naming.lookup("rmi://localhost/UserServer");
            controller.setUserServer(userServer);
            GameServer gameServer = (GameServer) Naming.lookup("rmi://localhost/GameServer");
            controller.setGameServer(gameServer);
            TeamServer teamServer = (TeamServer) Naming.lookup("rmi://localhost/TeamServer");
            controller.setTeamServer(teamServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HBox rootPane = controller.createMainPane();
        EventHandler<ActionEvent> handler = new ButtonEventHandler();
        controller.getAddTeamBtn().setOnAction(handler);
        controller.getGameBtn().setOnAction(handler);
        controller.getCreateUserBtn().setOnAction(handler);
        controller.getModifyBtn().setOnAction(handler);
        controller.getBlockBtn().setOnAction(handler);
        controller.getResultBtn().setOnAction(handler);

        Scene scene = new Scene(rootPane, 700, 300);

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(340);
        primaryStage.setMinHeight(260);
        primaryStage.setTitle("Management tool");
        primaryStage.show();
    }

}
