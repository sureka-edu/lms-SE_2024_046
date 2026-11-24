package lms.GUI;

import javax.swing.*;
import java.awt.*;
import lms.service.Library;
import lms.service.LibraryController;
import lms.model.Book;
import lms.model.DVD;
import lms.model.Magazine;
import lms.model.Student;
import lms.model.Staff;
public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Library library;
    private LibraryController controller;
    private BooksPanel booksPanel;
    private MembersPanel membersPanel;
   private MagazinePanel magazinePanel;
    public MainFrame(){
        setTitle("Library Management System");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        library = new Library();
        controller = new LibraryController(library);
        controller.addItem(new Book("B01","Java","Jack"));
        controller.addItem(new Book("B02","Python","Kevin"));
        controller.addItem(new DVD("D01","Data Structures",120));
        controller.addItem(new Magazine("MG01","Technology Today",15));
        controller.addItem(new Magazine("MG02","Computer World",20));
        controller.addMember(new Student("M01","Amal"));
        controller.addMember(new Student("M02","Nimal"));
        controller.addMember(new Staff("M03","Kamal"));
        cardLayout=new CardLayout();
        mainPanel=new JPanel(cardLayout);
        booksPanel=new BooksPanel(controller);
        membersPanel=new MembersPanel(controller);
        magazinePanel = new MagazinePanel(controller);
        mainPanel.add(new DashboardPanel(cardLayout, mainPanel),"Dashboard");
        mainPanel.add(booksPanel,"Books");
        mainPanel.add(membersPanel,"Members");
        mainPanel.add(magazinePanel,"Magazines");
        mainPanel.add(new BorrowPanel(controller,this::refreshTables),"Borrow");
        mainPanel.add(new ReturnPanel(controller,this::refreshTables),"Return");
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
        magazinePanel.refreshTable();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->new MainFrame());
    }
}
