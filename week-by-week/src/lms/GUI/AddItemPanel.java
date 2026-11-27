package lms.GUI;

import javax.swing.*;
import java.awt.*;
import lms.service.LibraryController;

public class AddItemPanel extends JPanel{
    private LibraryController controller;
    private Runnable refreshCallback;
    private java.util.function.Consumer<String> statusCallback;
    private JComboBox<String> typeBox;
    private JTextField idField;
    private JTextField titleField;
    private JTextField detailField;
    public AddItemPanel(LibraryController controller,Runnable refreshCallback,java.util.function.Consumer<String> statusCallback){
        this.controller=controller;
        this.refreshCallback=refreshCallback;
        this.statusCallback=statusCallback;
        setLayout(new BorderLayout(10,10));
        JLabel heading=new JLabel("Add Library Item",SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,22));
        JPanel formPanel = new JPanel(new GridLayout(4,2,10,10));
        typeBox=new JComboBox<>(new String[]{"Book", "DVD", "Magazine"});
        idField=new JTextField();
        titleField=new JTextField();
        detailField=new JTextField();
        formPanel.add(new JLabel("Item Type:"));
        formPanel.add(typeBox);
        formPanel.add(new JLabel("Item ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Author/ Duration/ Issue Number:"));
        formPanel.add(detailField);
        JButton addButton =new JButton("Add Item");
        JButton clearButton= new JButton("Clear");
        JPanel buttonPanel =new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        addButton.addActionListener(e->onAdd());
        clearButton.addActionListener(e->clearFields());
        JButton backButton=new JButton("back to dashboard");
        backButton.addActionListener(e->{
            Container parent = getParent();
            if(parent instanceof JPanel){
                CardLayout layout=(CardLayout)parent.getLayout();
                layout.show(parent,"Dashboard");
            }
        });
        JPanel bottomPanel=new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel,BorderLayout.CENTER);
        bottomPanel.add(backButton,BorderLayout.SOUTH);
        add(heading,BorderLayout.NORTH);
        add(formPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    private void onAdd(){
        String type=(String)typeBox.getSelectedItem();
        String id=idField.getText().trim();
        String title=titleField.getText().trim();
        String detail=detailField.getText().trim();
        if(id.isEmpty() || title.isEmpty() || detail.isEmpty()){
            JOptionPane.showMessageDialog(this,"fill in all fields","Input Error",JOptionPane.WARNING_MESSAGE);
            return;
        }
        try{
            controller.addItem(type,id,title,detail);
            refreshCallback.run();
            statusCallback.accept("Successfully added item: "+id);
            JOptionPane.showMessageDialog(this,"item added successfully","success",JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this,e.getMessage(),"invalid Input",JOptionPane.WARNING_MESSAGE);
        }
    }
    private void clearFields(){
        idField.setText("");
        titleField.setText("");
        detailField.setText("");
        typeBox.setSelectedIndex(0);
    }
}
