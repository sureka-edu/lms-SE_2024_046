package lms.service;

import lms.model.LibraryItem;
import lms.model.Member;
import lms.ItemNotAvailableException;
import lms.ItemNotBorrowedException;
import lms.BorrowLimitExceededException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Library{
    private List<LibraryItem> items;
    private List<Member> members;
    private Map<LibraryItem, Member> borrowedItems;
    public Library(){
        items=new ArrayList<>();
        members=new ArrayList<>();
        borrowedItems =new HashMap<>();
    }
    public void addItem(LibraryItem item){
        items.add(item);
    }
    public void addMember(Member member){
        members.add(member);
    }
    public List<LibraryItem> getItems(){
        return items;
    }
    public List<Member> getMembers(){
        return members;
    }
    public void displayItems(){
        for(LibraryItem item :items){
            System.out.println(item);
        }
    }
    public void displayMembers(){
        for(Member member :members){
            System.out.println(member);
        }
    }

    public void displayLateFees(int daysLate){
        for(LibraryItem item : items){
            System.out.println(item.getTitle()+"-Late Fee: Rs. "+item.calculateLateFee(daysLate));
        }
    }
    public void borrowItem(LibraryItem item,Member member)
            throws ItemNotAvailableException, BorrowLimitExceededException{
        if(item.isBorrowed()){
            throw new ItemNotAvailableException("This item is already borrowed");
        }
        if(member.getBorrowedCount() >= member.getMaxBorrowLimit()){
            throw new BorrowLimitExceededException("Member has reached the borrowing limit");
        }
        item.setBorrowed(true);
        member.setBorrowedCount(member.getBorrowedCount()+1);
        borrowedItems.put(item, member);
        System.out.println("Item borrowed successfully");
    }
    public void returnItem(LibraryItem item)
            throws ItemNotBorrowedException {
        if (!item.isBorrowed()){
            throw new ItemNotBorrowedException(
                    "This item has not been borrowed");
        }
        Member member = borrowedItems.get(item);
        if(member == null){
            throw new ItemNotBorrowedException("Borrower info not found");
        }
        item.setBorrowed(false);
        member.setBorrowedCount(member.getBorrowedCount() - 1);
        borrowedItems.remove(item);
        System.out.println("Item returned successfully");
    }
}
