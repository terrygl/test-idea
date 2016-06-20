package joe.spring.springapp.data.reference;

public enum AccountType {
	FREE (1L, "Free"),
	PREMIUM (2L, "Premium");

    private final Long id;  
    private final String type; 

    AccountType(Long id, String type) {
        this.id = id;
        this.type = type;
    }
    public Long id() { return id; }
    public String type() { return type; }	
	
}
