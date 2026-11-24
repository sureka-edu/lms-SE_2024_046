package lms.GUI;

import javax.swing.*;
import java.awt.*;
import lms.service.Library;
import lms.model.LibraryItem;
import lms.ItemNotBorrowedException;
public class ReturnPanel extends JPanel{
    private Runnable refreshCallback;
    public ReturnPanel(Library library, Runnable refreshCallback){
        this.refreshCallback = refreshCallback;
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Return Library Item",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        JPanel formPanel=new JPanel(new GridLayout(2,2,10,10));
        JLabel itemIdLabel=new JLabel("Item ID:");
        JTextField itemIdField=new JTextField();
        JButton returnButton=new JButton("Return");
        JButton clearButton=new JButton("Clear");
        formPanel.add(itemIdLabel);
        formPanel.add(itemIdField);
        formPanel.add(returnButton);
        formPanel.add(clearButton);
        returnButton.addActionListener(e ->{
            String itemId=itemIdField.getText().trim();
            if (itemId.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter an Item ID");
                return;
            }
            LibraryItem selectedItem = null;
            for(LibraryItem item : library.getItems()){
                if(item.getId().equalsIgnoreCase(itemId)){
                    selectedItem = item;
                    break;
                }
            }
            if (selectedItem==null){
                JOptionPane.showMessageDialog(this, "item not found");
                return;
            }
            if(!selectedItem.isBorrowed()){
                JOptionPane.showMessageDialog(this,"this item has not been borrowed");
                return;
            }
            try{
                library.returnItem(selectedItem);
                refreshCallback.run();
                JOptionPane.showMessageDialog(this,"Item returned successfully");
            }catch(ItemNotBorrowedException ex){
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Return Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        clearButton.addActionListener(e->itemIdField.setText(""));
        JButton backButton=new JButton("Back to Dashboard");
        backButton.addActionListener(e->{
            Container parent = getParent();
            if(parent instanceof JPanel){
                CardLayout layout=(CardLayout)parent.getLayout();
                layout.show(parent,"Dashboard");
            }
        });
        add(heading,BorderLayout.NORTH);
        add(formPanel,BorderLayout.CENTER);
        add(backButton,BorderLayout.SOUTH);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Return Details"),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));
    }
}
