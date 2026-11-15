package lms.model;

public class Book extends LibraryItem{
    private String author;
    public Book(String id, String title, String author){
        super(id, title);
        this.author = author;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    @Override
    public void displayDetails(){
        System.out.println("Book Details");
        System.out.println("ID: "+getId());
        System.out.println("Title: "+getTitle());
        System.out.println("Author: "+author);
        System.out.println("Borrowed: "+isBorrowed());
    }
}
