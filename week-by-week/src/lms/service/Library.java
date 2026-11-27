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
    public boolean hasItem(String id){
        for(LibraryItem item : items){
            if(item.getId().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
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
        item.setBorrower(member);
        member.setBorrowedCount(member.getBorrowedCount()+1);
        borrowedItems.put(item,member);
    }
    public void returnItem(LibraryItem item)
            throws ItemNotBorrowedException {
        if (!item.isBorrowed()){
            throw new ItemNotBorrowedException("This item has not been borrowed");
        }
        Member member = borrowedItems.get(item);
        if(member == null){
            throw new ItemNotBorrowedException("Borrower info not found");
        }
        item.setBorrowed(false);
        item.setBorrower(null);
        member.setBorrowedCount(member.getBorrowedCount()-1);
        borrowedItems.remove(item);
    }
}
