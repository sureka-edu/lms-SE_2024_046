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
    }
}
