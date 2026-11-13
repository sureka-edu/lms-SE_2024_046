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
}
