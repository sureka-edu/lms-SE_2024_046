package lms.GUI;

import javax.swing.SwingUtilities;
import lms.service.Library;
import lms.service.LibraryController;
import lms.model.Member;

public class GuiMain{
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            Library library=new Library();
            LibraryController controller=new LibraryController(library);
            controller.addMember(new Member("M001","Nadeesha Perera",3));
            controller.addMember(new Member("M002","Amal Fernando",3));
            controller.addItem("Book","B001","Java Programming","James");
            controller.addItem("DVD","D001","Java Tutorial","120");
            controller.addItem("Magazine","MG001","IT Monthly","5");
            new MainFrame(controller);
        });
    }
}
