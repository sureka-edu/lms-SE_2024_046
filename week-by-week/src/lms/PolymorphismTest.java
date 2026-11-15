package lms;

import lms.model.LibraryItem;
import lms.model.Book;
import lms.model.DVD;
public class PolymorphismTest {
    public static void main(String[] args) {
        LibraryItem item;
        item = new Book("B01", "Java Programming", "James");
        System.out.println("Book late fee: Rs. " + item.calculateLateFee(3));
        item = new DVD("D01", "Java Tutorial", 120);
        System.out.println("DVD late fee: Rs. " + item.calculateLateFee(3));
        Library library = new Library();
        library.addItem(new Book("B01", "Java Programming", "James"));
        library.addItem(new DVD("D01", "Java Tutorial", 120));
        library.displayLateFees(3);
    }
    LibraryItem item = new DVD("D001", "Avengers", 150);
    item.displayDetails();
    System.out.println("Late Fee: Rs. " + item.calculateLateFee(2));
}
