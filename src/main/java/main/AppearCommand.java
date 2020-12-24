package main;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AppearCommand implements TransformCommand {
    private AnchorPane receiver;

    public AppearCommand(AnchorPane receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        //設定receiver的Visible為true
        receiver.setVisible(true);

        //將Pane往右移
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), receiver);
        translateTransition1.setByX(+600);
        translateTransition1.play();
    }
}
