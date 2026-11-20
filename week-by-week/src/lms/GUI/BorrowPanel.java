package lms.gui;

import javax.swing.*;
import java.awt.*;
public class BorrowPanel extends JPanel{
    public BorrowPanel(){
        setLayout(new BorderLayout(10,10));
        JLabel heading = new JLabel("Borrow Library Item",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        JPanel formPanel=new JPanel(new GridLayout(4,2,10,10));
        JLabel itemTypeLabel=new JLabel("Item Type:");
        JLabel itemIdLabel=new JLabel("Item ID:");
        JLabel memberIdLabel=new JLabel("Member ID:");
        String[] itemTypes = {"Book", "DVD"};
        JComboBox<String> itemTypeBox = new JComboBox<>(itemTypes);
        JTextField itemIdField = new JTextField();
        JTextField memberIdField = new JTextField();
        JButton borrowButton = new JButton("Borrow");
        JButton clearButton = new JButton("Clear");
        formPanel.add(itemTypeLabel);
        formPanel.add(itemTypeBox);
        formPanel.add(itemIdLabel);
        formPanel.add(itemIdField);
        formPanel.add(memberIdLabel);
        formPanel.add(memberIdField);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        buttonPanel.add(borrowButton);
        buttonPanel.add(clearButton);
        formPanel.add(new JLabel(""));
        formPanel.add(buttonPanel);
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e->{Container parent = getParent();
        if (parent instanceof JPanel) {
            CardLayout layout=(CardLayout)parent.getLayout();
            layout.show(parent, "Dashboard");
        }
        });
        add(heading, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Borrow Details"),
            BorderFactory.createEmptyBorder(20,20,20,20)
        ));
    }
}
