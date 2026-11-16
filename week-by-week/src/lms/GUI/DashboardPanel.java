package lms.gui;
import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel{
    public DashboardPanel(){
        setLayout(new GridLayout(2, 2, 10, 10));
        JButton booksButton=new JButton("Books");
        JButton membersButton=new JButton("Members");
        JButton borrowButton=new JButton("Borrow Items");
        JButton returnButton=new JButton("Return Items");
        add(booksButton);
        add(membersButton);
        add(borrowButton);
        add(returnButton);
    }
}
