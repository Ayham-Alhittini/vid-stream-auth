package videostreaming.authenticationservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "users")
@Entity
public class AppUser {

    public AppUser() {}

    public AppUser(String userName, String knownAs, String email, String password) {
        this.userName = userName;
        this.knownAs = knownAs;
        this.email = email;
        this.password = password;
    }

    @Id
    private String userName;

    @Column
    private String knownAs;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKnownAs() {
        return knownAs;
    }

    public void setKnownAs(String knownAs) {
        this.knownAs = knownAs;
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
}
