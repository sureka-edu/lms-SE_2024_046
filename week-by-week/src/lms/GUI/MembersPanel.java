package lms.gui;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;

public class MembersPanel extends JPanel{
    public MembersPanel(){
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Members Management",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        String[] columns={"Member ID", "Name","Borrow Limit","Borrowed Items"};
        DefaultTableModel model=new DefaultTableModel(columns,0);
        model.addRow(new Object[]{"M01","Amal",3,1});
        model.addRow(new Object[]{"M02","Nimal",5,2});
        model.addRow(new Object[]{"M03","Kamal",3,0});
        JTable membersTable=new JTable(model);
        membersTable.setRowHeight(30);
        membersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        membersTable.setFillsViewportHeight(true);
        JScrollPane scrollPane=new JScrollPane(membersTable);
        add(heading,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JButton backButton=new JButton("Back to Dashboard");
        backButton.addActionListener(e->{Container parent = getParent();
            if(parent instanceof JPanel){
                CardLayout layout=(CardLayout)parent.getLayout();
                layout.show(parent,"Dashboard");
            }
        });
        add(backButton,BorderLayout.SOUTH);
    }
}
