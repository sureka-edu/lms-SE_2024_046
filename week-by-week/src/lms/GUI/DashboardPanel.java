package lms.GUI;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel{
    public DashboardPanel(CardLayout cardLayout, JPanel mainPanel){
        setLayout(new BorderLayout(10,10));
        JLabel heading = new JLabel("Library Management System",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,24));
        JPanel buttonPanel = new JPanel(new GridLayout(3,2,10,10));
        JButton booksButton =new JButton("Books");
        JButton membersButton =new JButton("Members");
        JButton borrowButton =new JButton("Borrow Items");
        JButton returnButton =new JButton("Return Items");
        JButton magazineButton =new JButton("Magazines");
        JButton addItemButton = new JButton("Add Item");
        Dimension buttonSize=new Dimension(150,80);
        booksButton.addActionListener(e->cardLayout.show(mainPanel,"Books"));
        membersButton.addActionListener(e->cardLayout.show(mainPanel,"Members"));
        borrowButton.addActionListener(e->cardLayout.show(mainPanel,"Borrow"));
        returnButton.addActionListener(e->cardLayout.show(mainPanel, "Return"));
        magazineButton.addActionListener(e->cardLayout.show(mainPanel,"Magazines"));
        addItemButton.addActionListener(e->cardLayout.show(mainPanel, "AddItem"));
        booksButton.setPreferredSize(buttonSize);
        membersButton.setPreferredSize(buttonSize);
        borrowButton.setPreferredSize(buttonSize);
        returnButton.setPreferredSize(buttonSize);
        magazineButton.setPreferredSize(buttonSize);
        addItemButton.setPreferredSize(buttonSize);
        buttonPanel.add(booksButton);
        buttonPanel.add(membersButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(magazineButton);
        buttonPanel.add(addItemButton);
        add(heading,BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JLabel footer = new JLabel("Welcome to the Library Management System",SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN,14));
        add(footer, BorderLayout.SOUTH);
    }
}
