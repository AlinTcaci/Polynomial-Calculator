import controller.ControllerMainView;
import GUI.MainView;

public class App {
    public static void main(String[] args) {
        MainView view = new MainView();
        ControllerMainView loginController = new ControllerMainView(view);
        view.setVisible(true);
    }
}
