package lms.gui;
import javax.swing.*;
import java.awt.*;
public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    public MainFrame(){
        setTitle("Library Management System");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(new DashboardPanel(),"Dashboard");
        mainPanel.add(new BooksPanel(),"Books");
        mainPanel.add(new MembersPanel(),"Members");
        mainPanel.add(new BorrowPanel(),"Borrow");
        mainPanel.add(new ReturnPanel(),"Return");
        add(mainPanel);
        cardLayout.show(mainPanel, "Dashboard");
        setVisible(true);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
