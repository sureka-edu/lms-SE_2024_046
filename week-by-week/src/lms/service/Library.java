package lms.service;

import lms.model.LibraryItem;
import lms.model.Member;
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
}
