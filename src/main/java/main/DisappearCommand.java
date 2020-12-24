package main;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class DisappearCommand implements TransformCommand {
    private AnchorPane receiver;

    public DisappearCommand(AnchorPane receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //將Pane往左縮
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), receiver);
        translateTransition1.setByX(-600);
        translateTransition1.play();

        //動畫結束之後將receiver設為隱形
        translateTransition1.setOnFinished(event1 -> {
            receiver.setVisible(false);
        });
    }
}
