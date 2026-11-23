package lms;
import lms.service.Library;
import lms.model.Member;
import lms.ItemNotAvailableException;
import lms.ItemNotBorrowedException;
import lms.BorrowLimitExceededException;
import lms.model.LibraryItem;
import lms.model.Book;
import lms.model.DVD;
public class PolymorphismTest{
public static void main(String[] args) {
    LibraryItem item;
    item = new Book("B01","Python","James");
    item.displayDetails();
    System.out.println("Book late fee: Rs. " + item.calculateLateFee(3));
    System.out.println();
    item = new DVD("D01","Java",120);
    item.displayDetails();
    System.out.println("DVD late fee: Rs. "+item.calculateLateFee(3));
    System.out.println();
    Library library = new Library();
    Book book = new Book("B02", "C program", "Dennis Ritchie");
    DVD dvd = new DVD("D02", "Rust", 120);
    library.addItem(book);
    library.addItem(dvd);
    library.displayLateFees(3);
    Member member = new Member("M01","Jack",3);
    System.out.println("Exception Handling Tests");
    try{
        library.borrowItem(book, member);
    }catch(ItemNotAvailableException|BorrowLimitExceededException e){
        System.out.println("Error: "+e.getMessage());
    }
    try{
        library.borrowItem(book,member);
    }catch (ItemNotAvailableException | BorrowLimitExceededException e){
        System.out.println("Exception caught: "+e.getMessage());
    }
    try{
        library.returnItem(book,member);
    } catch (ItemNotBorrowedException e){
        System.out.println("Error: "+e.getMessage());
    }
    try{
        library.returnItem(book,member);
    } catch (ItemNotBorrowedException e){
        System.out.println("Exception caught: "+e.getMessage());
    }
    System.out.println("borrow limit test");
    try{
        library.borrowItem(dvd, member);
        library.borrowItem(new Book("B03", "C++", "Bjarne"), member);
        library.borrowItem(new Book("B04", "Python", "Guido"), member);
        library.borrowItem(new Book("B05", "Data Structures", "Mark"), member);
    
    }catch(ItemNotAvailableException | BorrowLimitExceededException e){
        System.out.println("Exception caught: " + e.getMessage());
    }
}
}
