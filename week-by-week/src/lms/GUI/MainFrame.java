package lms.gui;

import javax.swing.*;
import java.awt.*;
import lms.service.Library;
import lms.model.Book;
import lms.model.DVD;
import lms.model.Student;
import lms.model.Staff;
public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Library library;
    private BooksPanel booksPanel;
    private MembersPanel membersPanel;
    public MainFrame(){
        setTitle("Library Management System");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        library = new Library();
        library.addItem(new Book("B01","Java","Jack"));
        library.addItem(new Book("B02","Python","Kevin"));
        library.addItem(new DVD("D01","Data Structures",120));
        library.addMember(new Student("M01","Amal"));
        library.addMember(new Student("M02","Nimal"));
        library.addMember(new Staff("M03","Kamal"));
        cardLayout=new CardLayout();
        mainPanel=new JPanel(cardLayout);
        booksPanel = new BooksPanel(library);
        membersPanel = new MembersPanel(library);
        mainPanel.add(new DashboardPanel(cardLayout, mainPanel),"Dashboard");
        mainPanel.add(new BooksPanel(library),"Books");
        mainPanel.add(new MembersPanel(library),"Members");
        mainPanel.add(new BorrowPanel(library),"Borrow");
        mainPanel.add(new ReturnPanel(library),"Return");
        add(mainPanel, BorderLayout.CENTER);
        JLabel statusBar=new JLabel("Library Management System");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);
        cardLayout.show(mainPanel,"Dashboard");
        setVisible(true);
    }
    public void refreshTables(){
        booksPanel.refreshTable();
        membersPanel.refreshTable();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->new MainFrame());
    }
}
