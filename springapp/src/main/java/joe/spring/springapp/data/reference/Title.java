package joe.spring.springapp.data.reference;

public enum Title {
	MR (1L, "Mr."),
	MS (2L, "Ms."),
	MRS (3L, "Mrs.");

    private final Long id;  
    private final String title; 

    Title(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    public Long id() { return id; }
    public String title() { return title; }	
	
}
