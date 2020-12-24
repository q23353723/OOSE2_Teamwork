package main;

import form.AdminView;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class GraphicFormattingStrategy implements FormattingStrategy {
    AdminView view;

    public GraphicFormattingStrategy(AdminView view) {
        this.view = view;
    }

    @Override
    public void format() {
        int x = 260;
        int y = 94;
        //將所有image設為visible
        for (ImageView image : view.getImgList()) {
            image.setVisible(true);
        }
        //排版
        ArrayList<Label> labelList = view.getLabelList();
        for (int i = 0; i <= 2; i++) {
            labelList.get(i).setLayoutX(x);
            labelList.get(i).setLayoutY(y);
            y += 183;
        }
        x += 412;
        y = 94;
        for (int i = 3; i <= 5; i++) {
            labelList.get(i).setLayoutX(x);
            labelList.get(i).setLayoutY(y);
            y += 183;
        }
    }
}
