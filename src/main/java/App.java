import controller.ProjectController;
import model.TextToPromptManager;
public class App {
    public static void main(String[] args) {
        ProjectController projectController = new ProjectController();
        projectController.run();
    }
}
