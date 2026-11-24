package lms.GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import lms.service.LibraryController;
import lms.model.LibraryItem;
import lms.model.Book;
public class BooksPanel extends JPanel{
    private LibraryController controller;
    private DefaultTableModel tableModel;
    public BooksPanel(LibraryController controller){
        this.controller = controller;
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Books Management",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        String[] columns={"Book ID","Title","Author","Status"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable booksTable=new JTable(tableModel);
        booksTable.setRowHeight(30);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        booksTable.setFillsViewportHeight(true);
        JScrollPane scrollPane=new JScrollPane(booksTable);
        add(heading, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JButton backButton=new JButton("Back to Dashboard");
        backButton.addActionListener(e->{
            Container parent=getParent();
            if(parent instanceof JPanel){
                CardLayout layout=(CardLayout)parent.getLayout();
                layout.show(parent,"Dashboard");
            }
        });
        add(backButton,BorderLayout.SOUTH);
        refreshTable();
    }
    public void refreshTable(){
        tableModel.setRowCount(0);
        for(LibraryItem item : controller.getItems()){
            if(item instanceof Book){
                Book book=(Book)item;
                String status=item.isBorrowed()? "Borrowed":"Available";
                tableModel.addRow(new Object[]{item.getId(),item.getTitle(),book.getAuthor(),status});
            }
        }
    }
}
