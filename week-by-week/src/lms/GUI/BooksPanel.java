package lms.gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        DefaultTableModel tableModel=new DefaultTableModel(data,columns);
        JTable booksTable=new JTable(tableModel);
        booksTable.setRowHeight(30);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        booksTable.setFillsViewportHeight(true);
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
