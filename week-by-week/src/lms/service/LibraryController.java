package lms.service;

import lms.model.LibraryItem;
import lms.model.Member;
import lms.model.Book;
import lms.model.DVD;
import lms.model.Magazine;
import lms.ItemNotAvailableException;
import lms.ItemNotBorrowedException;
import lms.BorrowLimitExceededException;
import java.util.List;

public class LibraryController{
    private Library library;
    public LibraryController(Library library){
        this.library = library;
    }
    public List<LibraryItem> getItems(){
        return library.getItems();
    }
    public List<Member> getMembers(){
        return library.getMembers();
    }
    public boolean addItem(LibraryItem item){
        if (library.hasItem(item.getId())){
            return false;
        }
        library.addItem(item);
        return true;
    }
    public void addMember(Member member){
        library.addMember(member);
    }
    public void borrowItem(String itemId,String memberId,String itemType)
            throws ItemNotAvailableException, BorrowLimitExceededException{

        LibraryItem selectedItem= null;
        Member selectedMember= null;
        for(LibraryItem item : library.getItems()){
            if(item.getId().equalsIgnoreCase(itemId)){
                if((itemType.equals("Book")&&item instanceof Book) ||(itemType.equals("DVD")&&item instanceof DVD) ||(itemType.equals("Magazine") && item instanceof Magazine)){
                    selectedItem =item;
                    break;
                }
            }
        }
        for(Member member :library.getMembers()){
            if(member.getMemberId().equalsIgnoreCase(memberId)){
                selectedMember =member;
                break;
            }
        }
        if(selectedItem==null){
            throw new IllegalArgumentException("Item not found");
        }
        if(selectedMember == null){
            throw new IllegalArgumentException("Member not found");
        }
        library.borrowItem(selectedItem,selectedMember);
    }
    public void returnItem(String itemId)throws ItemNotBorrowedException{
        LibraryItem selectedItem = null;
        for(LibraryItem item : library.getItems()){
            if(item.getId().equalsIgnoreCase(itemId)) {
                selectedItem = item;
                break;
            }
        }
        if (selectedItem == null){
            throw new IllegalArgumentException("Item not found");
        }
        library.returnItem(selectedItem);
    }
}

