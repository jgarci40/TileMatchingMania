package edu.uci.Inf122.TileMatchingMania.GUI.GameRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Supplier;

public class LoginPanel extends JPanel implements ActionListener {

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit;
    Runnable callBack;
    String text = "";

    public LoginPanel() {
        user_label = new JLabel();
        user_label.setText(text);
        userName_text = new JTextField();

        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();

        // Submit

        submit = new JButton("SUBMIT");
        submit.addActionListener(this);

        panel = new JPanel(new GridLayout(3, 1));

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);

        message = new JLabel();
        panel.add(message);
        panel.add(submit);

        add(panel);
    }

    public void setCallBack(Runnable runnable) {
        this.callBack = runnable;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();
        callBack.run();
    }
}
