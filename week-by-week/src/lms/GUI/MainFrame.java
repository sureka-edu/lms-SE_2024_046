package lms.gui;
import javax.swing.JFrame;
public class MainFrame extends JFrame{
    public MainFrame(){
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args){
        new MainFrame();
    }
}
