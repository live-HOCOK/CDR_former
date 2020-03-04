
import common.Loggers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Main extends Application {
    private static Logger logger = Loggers.Companion.getFileLogger();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui_resources/sample.fxml"));
        primaryStage.setTitle("CDR former");
        primaryStage.setScene(new Scene(root, 900, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        if (args.length > 0) {
            setLogLevel(args[0]);
        }
        launch(args);
    }

    private static void setLogLevel(String level) {
        Configurator.setLevel("FileLogger", Level.valueOf(level));
    }
}
