package lms.gui;

import javax.swing.*;
import java.awt.*;
public class ReturnPanel extends JPanel{
    public ReturnPanel(){
        setLayout(new GridLayout(3, 2, 10, 10));
        JLabel heading = new JLabel("Return Library Item");
        JLabel itemIdLabel = new JLabel("Item ID:");
        JTextField itemIdField = new JTextField();
        JButton returnButton = new JButton("Return");
        JButton clearButton = new JButton("Clear");
        add(heading);
        add(new JLabel(""));
        add(itemIdLabel);
        add(itemIdField);
        add(returnButton);
        add(clearButton);
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
    }
}
