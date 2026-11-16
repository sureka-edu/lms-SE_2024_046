package lms.service;

import lms.model.LibraryItem;
import lms.model.Member;
import lms.exception.ItemNotAvailableException;
import lms.exception.BorrowLimitExceededException;
import java.util.ArrayList;
import java.util.List;
public class Library {
    private List<LibraryItem> items;
    private List<Member> members;
    public Library() {
        items=new ArrayList<>();
        members=new ArrayList<>();
    }
    public void addItem(LibraryItem item){
        items.add(item);
    }
    public void addMember(Member member){
        members.add(member);
    }
    public void displayItems(){
        for (LibraryItem item : items){
            System.out.println(item);
        }
    }
    public void displayMembers(){
        for (Member member : members){
            System.out.println(member);
        }
    }
    public void displayLateFees(int daysLate) {
        for (LibraryItem item : items) {
            System.out.println(item.getTitle()+"-Late Fee:Rs. "+item.calculateLateFee(daysLate));
        }
    }
    public void borrowItem(LibraryItem item, Member member) throws ItemNotAvailableException, BorrowLimitExceededException{
        if (item.isBorrowed()){
            throw new ItemNotAvailableException("This item is already borrowed.");
        }
        if (member.getBorrowedCount()>=member.getMaxBorrowLimit()){
            throw new BorrowLimitExceededException("Member has reached the borrowing limit.");
        }
        item.setBorrowed(true);
        member.setBorrowedCount(member.getBorrowedCount() + 1);
        System.out.println("Item borrowed successfully!");
    }
}
