package lms.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import lms.service.LibraryController;
import lms.model.LibraryItem;
import lms.model.DVD;

public class DVDPanel extends JPanel{
    private LibraryController controller;
    private DefaultTableModel model;
    private JTable dvdTable;
    public DVDPanel(LibraryController controller){
        this.controller=controller;
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("DVD Management",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        String[] columns={"DVD ID","Title","Duration(Minutes)","Status"};
        model=new DefaultTableModel(columns,0);
        dvdTable=new JTable(model);
        dvdTable.setRowHeight(30);
        dvdTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dvdTable.setFillsViewportHeight(true);
        JScrollPane scrollPane=new JScrollPane(dvdTable);
        add(heading,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JButton backButton = new JButton("Back to Dashboard");
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
        for(LibraryItem item: controller.getItems()){
            if(item instanceof DVD){
                DVD dvd=(DVD)item;
                String status;
                if(item.isBorrowed()){
                    status = "On loan: "+item.getBorrower().getName();
                }else{
                    status="Available";
                }
                model.addRow(new Object[]{
                        dvd.getId(),
                        dvd.getTitle(),
                        dvd.getDurationMinutes(),
                        status
                });
            }
        }
    }
    public String getSelectedItemId(){
        int selectedRow =dvdTable.getSelectedRow();
        if(selectedRow == -1){
            return null;
        }
        return model.getValueAt(selectedRow,0).toString();
    }
}
