package main;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public int width = 1280;
    public int height = 720;

    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Animation animation;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        // GraphicsContext boiler plate to get canvas to draw on
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        Main.stage = stage;
        Main.gc = canvas.getGraphicsContext2D();

        // Where you can add your methods for user inputs
        scene.setOnKeyPressed(event -> { });
        scene.setOnKeyReleased(event -> { });
        scene.setOnKeyTyped(event -> { });
        scene.setOnMouseClicked(event -> { });
        scene.setOnMouseDragged(event -> { });
        scene.setOnMouseMoved(event -> { });
        scene.setOnMousePressed(event -> { });
        scene.setOnMouseReleased(event -> { });
        scene.setOnScroll(event -> { });

        // Update method that gets called (1 second [1000] / frames [-60] = frame rate [60fps])
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                updateApplication();
            }
        });

        // Animation boiler plate code with frame rate
        animation = new Timeline(keyFrame);
        animation.setCycleCount(Animation.INDEFINITE);  // make the application loop forever
        animation.setRate(-60);                         // to make the frame render immediately
        animation.play();

        // Window boiler plate to make it appear
        Main.stage.setOnCloseRequest(value -> closeApplication());
        Main.stage.setTitle("JavaFX Example Application");
        Main.stage.getIcons().add(new Image("file:appIcon.png"));
        Main.stage.setScene(scene);
        Main.stage.setResizable(false);
        Main.stage.show();
    }

    // Example Project Variables
    private int xpos = 500;
    private int ypos = 500;
    private int xspeed = 5;
    private int yspeed = 5;
    private int size = 50;

    // Method that calls every time the frame is to be rendered
    protected void updateApplication() {
        // Background
        gc.save();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        gc.restore();

        // Draw Square
        gc.setFill(Color.RED);
        gc.fillRect(xpos, ypos, size, size);

        // Movement Logic
        xpos += xspeed;
        ypos += yspeed;
        if (xpos > width - size || xpos < 0) xspeed *= -1;
        if (ypos > height - size || ypos < 0) yspeed *= -1;
    }

    // When you want to exit the application
    private void closeApplication() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }

}