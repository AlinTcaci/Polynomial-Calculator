package controller;

import data_models.Operations;
import GUI.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMainView {
    private final MainView mainView;

    public ControllerMainView(MainView mainView) {
        this.mainView = mainView;
        mainView.showResult(new Result());
    }

    class Result implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s1 = mainView.getJTextFiled1();
            String s2 = mainView.getJTextFiled2();
            String op = mainView.getOperation();
            mainView.showMessage(Operations.returnResult(s1, s2, op));
        }
    }
}
