package videostreaming.authenticationservice.dto;

public class UserDto {
    public String userName;
    public String knownAs;
    public String email;
    public String token;

    public UserDto() {}

    public UserDto(String userName, String knownAs, String email, String token) {
        this.userName = userName;
        this.knownAs = knownAs;
        this.email = email;
        this.token = token;
    }

}
