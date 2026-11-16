package lms.gui;

import javax.swing.*;
import java.awt.*;
public class BorrowPanel extends JPanel{
    public BorrowPanel(){
        setLayout(new GridLayout(4,2,10,10));
        JLabel heading = new JLabel("Borrow Library Item");
        JLabel itemIdLabel = new JLabel("Item ID:");
        JLabel memberIdLabel = new JLabel("Member ID:");
        JTextField itemIdField = new JTextField();
        JTextField memberIdField = new JTextField();
        JButton borrowButton = new JButton("Borrow");
        JButton clearButton = new JButton("Clear");
        add(heading);
        add(new JLabel(""));
        add(itemIdLabel);
        add(itemIdField);
        add(memberIdLabel);
        add(memberIdField);
        add(borrowButton);
        add(clearButton);
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
    }
}
