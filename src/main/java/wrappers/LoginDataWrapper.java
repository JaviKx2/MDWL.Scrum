package wrappers;

public class LoginDataWrapper {
    
    private String email;
    
    private String password;
    
    public LoginDataWrapper(){
        
    }

    public LoginDataWrapper(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    @Override
    public String toString() {
        return "LoginWrapper [email=" + email + ", password=" + password + "]";
    }

    @Override
    public int hashCode() {
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

}
