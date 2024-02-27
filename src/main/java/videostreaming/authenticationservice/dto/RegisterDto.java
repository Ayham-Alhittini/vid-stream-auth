package videostreaming.authenticationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterDto {
    @Pattern(regexp = "^(?:\\d+[a-zA-Z\\d]|(?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){1,38})$",
            message = "You allowed to use alphanumeric character and hyphens")
    public String userName;

    @NotBlank(message = "knownAs required")
    public String knownAs;

    @Email(message = "expect email")
    public String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "include at least one lowercase letter, one uppercase letter, one digit, and one special character, with a minimum length of 8 characters.")
    public String password;
}
