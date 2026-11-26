package lms.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;
import lms.service.LibraryController;
import lms.ItemNotAvailableException;
import lms.MemberNotFoundException;
import lms.model.Member;

public class BorrowPanel extends JPanel{
    private Runnable refreshCallback;
    private java.util.function.Consumer<String> statusCallback;
    private Supplier<String> selectedItemCallback;
    private LibraryController controller;
    private JComboBox<String> memberBox;
    public BorrowPanel(LibraryController controller,Runnable refreshCallback,java.util.function.Consumer<String> statusCallback,Supplier<String> selectedItemCallback){

        this.controller = controller;
        this.refreshCallback= refreshCallback;
        this.statusCallback =statusCallback;
        this.selectedItemCallback =selectedItemCallback;
        setLayout(new BorderLayout(10,10));
        JLabel heading = new JLabel("Borrow Library Item",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        JPanel formPanel = new JPanel(new GridLayout(2,2,10,10));
        JLabel itemIdLabel=new JLabel("Selected Item ID:");
        JLabel memberLabel=new JLabel("Select Member:");
        JTextField itemIdField=new JTextField();
        itemIdField.setEditable(false);
        memberBox=new JComboBox<>();
        for(Member member :controller.getMembers()){
            memberBox.addItem(member.getName()+" - " +member.getMemberId());
        }
        JButton borrowButton=new JButton("Borrow");
        JButton clearButton=new JButton("Clear");
        formPanel.add(itemIdLabel);
        formPanel.add(itemIdField);
        formPanel.add(memberLabel);
        formPanel.add(memberBox);
        formPanel.add(borrowButton);
        formPanel.add(clearButton);
        borrowButton.addActionListener(e->onBorrow(itemIdField));
        clearButton.addActionListener(e->{
            itemIdField.setText("");
            if(memberBox.getItemCount()>0){
                memberBox.setSelectedIndex(0);
            }
        });
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e->{
            Container parent=getParent();
            if(parent instanceof JPanel){
                CardLayout layout=(CardLayout)parent.getLayout();
                layout.show(parent,"Dashboard");
            }
        });
        add(heading,BorderLayout.NORTH);
        add(formPanel,BorderLayout.CENTER);
        add(backButton,BorderLayout.SOUTH);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Borrow Details"),BorderFactory.createEmptyBorder(20,20,20,20)));
    }
    private void onBorrow(JTextField itemIdField){
        String itemId=selectedItemCallback.get();

        if(itemId==null){
            JOptionPane.showMessageDialog(this,"select an item from the Books or Magazines table first","No Item Selected",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        itemIdField.setText(itemId);
        String selectedMember=(String)memberBox.getSelectedItem();
        if(selectedMember==null){
            JOptionPane.showMessageDialog(this,"select a member","Input Error",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String memberId=selectedMember.substring(selectedMember.lastIndexOf(" - ")+3);
        try{
            controller.borrow(itemId, memberId);
            refreshCallback.run();
            statusCallback.accept("Successfully borrowed item: "+itemId);
            JOptionPane.showMessageDialog(this,"Item borrowed successfully","Success",JOptionPane.INFORMATION_MESSAGE);
        }catch(ItemNotAvailableException | MemberNotFoundException ex){
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Borrow Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
