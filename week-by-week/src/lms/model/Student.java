package lms.model;

public class Student extends Member{
    private String universityId;
    public Student(String memberId,String name,String universityId){
        super(memberId, name);
        this.universityId = universityId;
    }
}
