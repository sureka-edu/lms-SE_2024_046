package lms.gui;

import javax.swing.*;
import java.awt.*;

public class ReturnPanel extends JPanel {
    public ReturnPanel() {
        setLayout(new BorderLayout(10, 10));
        JLabel heading = new JLabel("Return Library Item",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        JPanel formPanel = new JPanel(new GridLayout(2,2,10,10));
        JLabel itemIdLabel = new JLabel("Item ID:");
        JTextField itemIdField = new JTextField();
        JButton returnButton = new JButton("Return");
        JButton clearButton = new JButton("Clear");
        formPanel.add(itemIdLabel);
        formPanel.add(itemIdField);
        formPanel.add(returnButton);
        formPanel.add(clearButton);
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e->{Container parent=getParent();
        if (parent instanceof JPanel){
            CardLayout layout=(CardLayout) parent.getLayout();
            layout.show(parent, "Dashboard");
        }
        });
        add(heading, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    }
}
