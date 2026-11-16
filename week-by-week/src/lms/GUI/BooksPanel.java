package lms.gui;
import javax.swing.*;
import java.awt.*;
public class BooksPanel extends JPanel{
    public BooksPanel(){
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Books Management",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        String[] columns = {"Book ID","Title","Author","Status"};
        String[][] data = {
                {"B01", "Java Programming", "James", "Available"},
                {"B02", "C Programming", "Dennis", "Borrowed"},
                {"B03", "Database Systems", "Elmasri", "Available"}
        };
        JTable booksTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(booksTable);
        add(heading, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {Container parent=getParent();
        if (parent instanceof JPanel){
            CardLayout layout=(CardLayout) parent.getLayout();
            layout.show(parent, "Dashboard");
        }
        });
        add(backButton, BorderLayout.SOUTH);
    }
}
