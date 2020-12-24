package main;

import form.AdminView;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PlainTextFormattingStrategy implements FormattingStrategy {
    AdminView view;

    public PlainTextFormattingStrategy(AdminView view) {
        this.view = view;
    }

    @Override
    public void format() {
        int x = 100;
        int y = 50;
        //將所有image隱形
        for (ImageView image : view.getImgList()) {
            image.setVisible(false);
        }
        //排版
        for (Label label : view.getLabelList()) {
            label.setLayoutX(x);
            label.setLayoutY(y+=50);
        }
    }
}
