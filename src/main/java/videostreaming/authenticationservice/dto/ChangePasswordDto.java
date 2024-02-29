package videostreaming.authenticationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ChangePasswordDto {

    @NotBlank
    public String oldPassword;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$",
            message = "include at least one lowercase letter, one uppercase letter, one digit, and one special character, with a minimum length of 8 characters.")
    public String newPassword;
}
