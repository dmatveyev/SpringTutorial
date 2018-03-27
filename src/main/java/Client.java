public class Client {


    private String id;
    private String fullName;

    public Client(final String id, final String fullName) {
        this.fullName = fullName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public void setGreeting(String str) {
        
    }
}