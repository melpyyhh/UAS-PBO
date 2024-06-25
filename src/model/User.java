package model;

public class User {
    private String username;
    private String password;
    private String nim;
    private String email;

    public User(String username, String password, String nim, String email) {
        this.username = username;
        this.password = password;
        this.nim = nim;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getNim() {
    	return nim;
    }
    
    public String getEmail() {
    	return email;
    }
}
