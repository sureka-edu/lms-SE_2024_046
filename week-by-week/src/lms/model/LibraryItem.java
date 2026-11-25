package lms.model;

public abstract class LibraryItem {
    private String id;
    private String title;
    private boolean isBorrowed;
    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.isBorrowed = false;
    }
    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public boolean isBorrowed(){
        return isBorrowed;
    }
    private Member borrower;
    public void setId(String id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setBorrowed(boolean borrowed){
        isBorrowed = borrowed;
    }
    public double calculateLateFee(int daysLate){
        return daysLate * 10.0;
    }
    public abstract void displayDetails();
    public Member getBorrower(){
        return borrower;
    }
    public void setBorrower(Member borrower){
        this.borrower = borrower;
}
}
