package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JPanel basePanel;
    private JTextField polynomField1;
    private JTextField polynomField2;
    private JComboBox comboBoxOp;
    private JButton buttonResult;

    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public MainView() {
        setDimension(600, 400);
    }

    public String getJTextFiled1() {
        return this.polynomField1.getText();
    }

    public String getJTextFiled2() {
        return this.polynomField2.getText();
    }

    public String getOperation() {
        return (String) this.comboBoxOp.getSelectedItem();
    }

    public void showResult(ActionListener listener) {
        this.buttonResult.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(MainView.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

}
