package lms.model;
public class Member {
    private String memberId;
    private String name;
    private int borrowedCount;
    private int maxBorrowLimit;
    public Member(String memberId,String name,int maxBorrowLimit){
        this.memberId=memberId;
        this.name=name;
        this.maxBorrowLimit=maxBorrowLimit;
        this.borrowedCount=0;
    }
    public String getMemberId(){
        return memberId;
    }
    public String getName(){
        return name;
    }
    public int getBorrowedCount(){
        return borrowedCount;
    }
    public int getMaxBorrowLimit(){
        return maxBorrowLimit;
    }
    public void setMemberId(String memberId){
        this.memberId = memberId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBorrowedCount(int borrowedCount){
        this.borrowedCount = borrowedCount;
    }
    public void setMaxBorrowLimit(int maxBorrowLimit){
        this.maxBorrowLimit = maxBorrowLimit;
    }
}
