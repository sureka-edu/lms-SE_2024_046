package lms.GUI;

import javax.swing.*;
import java.awt.*;
import lms.service.LibraryController;

public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LibraryController controller;
    private BooksPanel booksPanel;
    private MembersPanel membersPanel;
    private MagazinePanel magazinePanel;
    private JLabel statusBar;
    private AddItemPanel addItemPanel;

    public MainFrame(LibraryController controller){
        this.controller=controller;
        setTitle("Library Management System");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout= new CardLayout();
        mainPanel =new JPanel(cardLayout);
        booksPanel= new BooksPanel(controller);
        membersPanel= new MembersPanel(controller);
        magazinePanel= new MagazinePanel(controller);
        addItemPanel = new AddItemPanel(controller,this::refreshTables,this::updateStatus);
        mainPanel.add(new DashboardPanel(cardLayout,mainPanel),"Dashboard");
        mainPanel.add(booksPanel,"Books");
        mainPanel.add(membersPanel,"Members");
        mainPanel.add(magazinePanel,"Magazines");
        mainPanel.add(new BorrowPanel(controller,this::refreshTables,this::updateStatus,this::getSelectedItemId),"Borrow");
        mainPanel.add(new ReturnPanel(controller,this::refreshTables,this::updateStatus,this::getSelectedItemId),"Return");
        mainPanel.add(addItemPanel, "AddItem");
        add(mainPanel,BorderLayout.CENTER);
        statusBar=new JLabel("Library Management System");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar,BorderLayout.SOUTH);
        cardLayout.show(mainPanel,"Dashboard");
        setVisible(true);
    }
    public String getSelectedItemId(){
        String bookId =booksPanel.getSelectedItemId();
        if(bookId !=null){
            return bookId;
        }
        String magazineId =magazinePanel.getSelectedItemId();
        if(magazineId !=null){
            return magazineId;
        }
        return null;
    }
    public void refreshTables(){
        booksPanel.refreshTable();
        membersPanel.refreshTable();
        magazinePanel.refreshTable();
    }
    public void updateStatus(String message){
        statusBar.setText(message);
    }
}
