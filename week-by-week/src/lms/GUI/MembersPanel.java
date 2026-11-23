package lms.GUI;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import lms.service.Library;
import lms.model.Member;
public class MembersPanel extends JPanel{
    private Library library;
    private DefaultTableModel model;
    public MembersPanel(Library library){
        this.library = library;
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Members Management",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        String[] columns={"Member ID","Name","Borrow Limit","Borrowed Items"};
        model = new DefaultTableModel(columns,0);
        JTable membersTable=new JTable(model);
        membersTable.setRowHeight(30);
        membersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        membersTable.setFillsViewportHeight(true);
        JScrollPane scrollPane=new JScrollPane(membersTable);
        add(heading,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
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
        model.setRowCount(0);
        for(Member member : library.getMembers()){
            model.addRow(new Object[]{member.getMemberId(),member.getName(),member.getMaxBorrowLimit(),member.getBorrowedCount()});
        }
    }
}
