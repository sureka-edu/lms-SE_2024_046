package lms.gui;

import javax.swing.*;
import java.awt.*;
import lms.service.Library;
import lms.model.LibraryItem;
import lms.model.Member;
import lms.model.Book;
import lms.model.DVD;
import lms.exception.ItemNotAvailableException;
import lms.exception.BorrowLimitExceededException;
public class BorrowPanel extends JPanel{
    private Runnable refreshCallback;
    public BorrowPanel(Library library,Runnable refreshCallback){
        this.refreshCallback = refreshCallback;
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Borrow Library Item",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        JPanel formPanel=new JPanel(new GridLayout(4,2,10,10));
        JLabel itemTypeLabel=new JLabel("Item Type:");
        JLabel itemIdLabel=new JLabel("Item ID:");
        JLabel memberIdLabel=new JLabel("Member ID:");
        String[] itemTypes={"Book","DVD"};
        JComboBox<String> itemTypeBox=new JComboBox<>(itemTypes);
        JTextField itemIdField=new JTextField();
        JTextField memberIdField=new JTextField();
        JButton borrowButton=new JButton("Borrow");
        JButton clearButton=new JButton("Clear");
        formPanel.add(itemTypeLabel);
        formPanel.add(itemTypeBox);
        formPanel.add(itemIdLabel);
        formPanel.add(itemIdField);
        formPanel.add(memberIdLabel);
        formPanel.add(memberIdField);
        JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        buttonPanel.add(borrowButton);
        buttonPanel.add(clearButton);
        formPanel.add(new JLabel(""));
        formPanel.add(buttonPanel);
        borrowButton.addActionListener(e->{
            String selectedType=(String)itemTypeBox.getSelectedItem();
            String itemId=itemIdField.getText().trim();
            String memberId=memberIdField.getText().trim();
            if (itemId.isEmpty()||memberId.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter both item ID and member ID");
                return;
            }
            LibraryItem selectedItem=null;
            Member selectedMember=null;
            for(LibraryItem item : library.getItems()){
                if(item.getId().equalsIgnoreCase(itemId)){
                    if ((selectedType.equals("Book") && item instanceof Book)||(selectedType.equals("DVD") && item instanceof DVD)){
                        selectedItem=item;
                        break;
                    }
                }
            }
            for(Member member : library.getMembers()){
                if(member.getMemberId().equalsIgnoreCase(memberId)){
                    selectedMember=member;
                    break;
                }
            }
            if (selectedItem==null){
                JOptionPane.showMessageDialog(this,"Item not found");
                return;
            }
            if (selectedMember==null){
                JOptionPane.showMessageDialog(this,"Member not found");
                return;
            }
            try{
                library.borrowItem(selectedItem,selectedMember);
                refreshCallback.run();
                JOptionPane.showMessageDialog(this,"Item borrowed successfully");
            }catch(ItemNotAvailableException | BorrowLimitExceededException ex){
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Borrow Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        clearButton.addActionListener(e->{
            itemTypeBox.setSelectedIndex(0);
            itemIdField.setText("");
            memberIdField.setText("");
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
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Borrow Details"),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));
    }
}
