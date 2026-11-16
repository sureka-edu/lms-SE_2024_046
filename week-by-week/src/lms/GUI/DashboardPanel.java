package lms.gui;
import javax.swing.*;
import java.awt.*;
public class DashboardPanel extends JPanel {
    public DashboardPanel() {
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Library Management System",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,24));
        JPanel buttonPanel=new JPanel(new GridLayout(2,2,10,10));
        JButton booksButton=new JButton("Books");
        JButton membersButton=new JButton("Members");
        JButton borrowButton=new JButton("Borrow Items");
        JButton returnButton=new JButton("Return Items");
        buttonPanel.add(booksButton);
        buttonPanel.add(membersButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        add(heading, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JLabel footer = new JLabel("Welcome to the Library Management System",SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 14));
        add(footer, BorderLayout.SOUTH);
    }
}
