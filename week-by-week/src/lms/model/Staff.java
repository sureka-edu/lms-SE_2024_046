package lms.model;

public class Staff extends Member{
    private String department;
    public Staff(String memberId,String name,String department){
        super(memberId,name);
        this.department = department;
    }
}
