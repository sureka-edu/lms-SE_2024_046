package lms.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;
import lms.service.LibraryController;
import lms.ItemNotAvailableException;

public class ReturnPanel extends JPanel{

    private Runnable refreshCallback;
    private java.util.function.Consumer<String> statusCallback;
    private Supplier<String> selectedItemCallback;
    private LibraryController controller;
    public ReturnPanel(LibraryController controller,Runnable refreshCallback,java.util.function.Consumer<String> statusCallback,Supplier<String> selectedItemCallback){
        this.controller=controller;
        this.refreshCallback= refreshCallback;
        this.statusCallback =statusCallback;
        this.selectedItemCallback =selectedItemCallback;
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Return Library Item",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        JPanel formPanel = new JPanel(new GridLayout(2,2,10,10));
        JLabel itemIdLabel =new JLabel("Selected Item ID:");
        JTextField itemIdField =new JTextField();
        itemIdField.setEditable(false);
        JButton returnButton=new JButton("Return");
        JButton clearButton=new JButton("Clear");
        formPanel.add(itemIdLabel);
        formPanel.add(itemIdField);
        formPanel.add(returnButton);
        formPanel.add(clearButton);
        returnButton.addActionListener(e->onReturn(itemIdField));
        clearButton.addActionListener(e->itemIdField.setText(""));
        JButton backButton=new JButton("Back to Dashboard");
        backButton.addActionListener(e->{
            Container parent=getParent();
            if(parent instanceof JPanel){
                CardLayout layout =(CardLayout)parent.getLayout();
                layout.show(parent,"Dashboard");
            }
        });
        add(heading,BorderLayout.NORTH);
        add(formPanel,BorderLayout.CENTER);
        add(backButton,BorderLayout.SOUTH);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Return Details"),BorderFactory.createEmptyBorder(20,20,20,20)));
    }
    private void onReturn(JTextField itemIdField){
        String itemId = selectedItemCallback.get();
        if(itemId == null){
            JOptionPane.showMessageDialog(this,"select an item from the Books or Magazines","no item selected",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        itemIdField.setText(itemId);
        try{
            controller.giveBack(itemId);
            refreshCallback.run();
            statusCallback.accept("Successfully returned item: "+itemId);
            JOptionPane.showMessageDialog(this,"Item returned successfully","Success",JOptionPane.INFORMATION_MESSAGE);
        }catch(ItemNotAvailableException ex){
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Return Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
