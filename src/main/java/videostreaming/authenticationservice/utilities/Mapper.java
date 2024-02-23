package videostreaming.authenticationservice.utilities;

import videostreaming.authenticationservice.dto.RegisterDto;
import videostreaming.authenticationservice.dto.UserDto;
import videostreaming.authenticationservice.models.AppUser;

public class Mapper {
    public static AppUser mapRegisterDtoToAppUser(RegisterDto registerDto) {
        return new AppUser(registerDto.userName, registerDto.knownAs, registerDto.email, registerDto.password);
    }

    public static UserDto mapAppUserToUserDto(AppUser appUser, String token) {
        return new UserDto(appUser.getUserName(), appUser.getKnownAs(), appUser.getEmail(), token);
    }
}
