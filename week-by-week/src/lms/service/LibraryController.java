package lms.service;

import lms.model.LibraryItem;
import lms.model.Member;
import lms.model.Book;
import lms.model.DVD;
import lms.model.Magazine;
import lms.ItemNotAvailableException;
import lms.ItemNotBorrowedException;
import lms.BorrowLimitExceededException;
import lms.MemberNotFoundException;

import java.util.List;

public class LibraryController{
    private Library library;
    public LibraryController(Library library){
        this.library=library;
    }
    public List<LibraryItem> getItems(){
        return library.getItems();
    }
    public List<Member> getMembers(){
        return library.getMembers();
    }
    public void addMember(Member member){
        library.addMember(member);
    }
    public void addItem(String type,String id,String title,String detail){
        if(library.hasItem(id)){
            throw new IllegalArgumentException("Item ID already exists");
        }
        LibraryItem item;
        switch(type){
            case "Book":
                item =new Book(id,title,detail);
                break;
            case "DVD":
                try{
                    int duration=Integer.parseInt(detail);
                    item =new DVD(id,title,duration);
                }catch(NumberFormatException e){
                    throw new IllegalArgumentException("Duration must be a whole number");
                }
                break;
            case "Magazine":
                try{
                    int issueNumber=Integer.parseInt(detail);
                    item =new Magazine(id,title,issueNumber);
                }catch(NumberFormatException e){
                    throw new IllegalArgumentException("Issue number must be a whole number");
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid item type");
        }
        library.addItem(item);
    }
    public void borrow(String itemId,String memberId)
            throws ItemNotAvailableException, MemberNotFoundException{
        LibraryItem selectedItem =null;
        Member selectedMember =null;

        for(LibraryItem item :library.getItems()){
            if(item.getId().equalsIgnoreCase(itemId)){
                selectedItem=item;
                break;
            }
        }

        for(Member member : library.getMembers()){
            if(member.getMemberId().equalsIgnoreCase(memberId)){
                selectedMember =member;
                break;
            }
        }
        if(selectedItem==null){
            throw new ItemNotAvailableException("Item not found");
        }
        if(selectedMember==null){
            throw new MemberNotFoundException("Member not found");
        }
        try{
            library.borrowItem(selectedItem,selectedMember);
        }catch (BorrowLimitExceededException e){
            throw new ItemNotAvailableException(e.getMessage());
        }
    }
    public void giveBack(String itemId)
            throws ItemNotAvailableException{
        LibraryItem selectedItem =null;
        for(LibraryItem item :library.getItems()){
            if(item.getId().equalsIgnoreCase(itemId)){
                selectedItem=item;
                break;
            }
        }
        if(selectedItem==null){
            throw new ItemNotAvailableException("Item not found");
        }
        try{
            library.returnItem(selectedItem);
        }catch(ItemNotBorrowedException e){
            throw new ItemNotAvailableException(e.getMessage());
        }
    }
}
