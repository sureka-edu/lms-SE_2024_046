package lms.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import lms.service.LibraryController;
import lms.model.LibraryItem;
import lms.model.Magazine;

public class MagazinePanel extends JPanel{
    private LibraryController controller;
    private DefaultTableModel model;
    private JTable magazineTable;
    public MagazinePanel(LibraryController controller){
        this.controller=controller;
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Magazine Management",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        String[] columns={"Magazine ID","Title","Issue Number","Status"};
        model=new DefaultTableModel(columns,0);
        magazineTable=new JTable(model);
        magazineTable.setRowHeight(30);
        magazineTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        magazineTable.setFillsViewportHeight(true);
        JScrollPane scrollPane=new JScrollPane(magazineTable);
        add(heading,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JButton backButton=new JButton("Back to Dashboard");
        backButton.addActionListener(e->{
            Container parent=getParent();
            if(parent instanceof JPanel){
                CardLayout layout=(CardLayout) parent.getLayout();
                layout.show(parent,"Dashboard");
            }
        });
        add(backButton,BorderLayout.SOUTH);
        refreshTable();
    }
    public void refreshTable(){
        model.setRowCount(0);
        for(LibraryItem item :controller.getItems()){
            if(item instanceof Magazine){
                Magazine magazine=(Magazine)item;
                String status;
                if(item.isBorrowed()){
                    status="On loan: "+item.getBorrower().getName();
                }else{
                    status="Available";
                }
                model.addRow(new Object[]{magazine.getId(),magazine.getTitle(),magazine.getIssueNumber(),status});
            }
        }
    }
    public String getSelectedItemId(){
        int selectedRow=magazineTable.getSelectedRow();
        if(selectedRow == -1){
            return null;
        }
        return model.getValueAt(selectedRow,0).toString();
    }
}
