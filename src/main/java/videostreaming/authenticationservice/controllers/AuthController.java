package videostreaming.authenticationservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import videostreaming.authenticationservice.data.AppUserRepository;
import videostreaming.authenticationservice.dto.LoginDto;
import videostreaming.authenticationservice.dto.RegisterDto;
import videostreaming.authenticationservice.dto.UserDto;
import videostreaming.authenticationservice.models.AppUser;
import videostreaming.authenticationservice.utilities.JWTUtility;
import videostreaming.authenticationservice.utilities.Mapper;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AuthController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {

        if (checkUserNameIsTaken(registerDto))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username is already taken.");

        if (checkEmailUsedBefore(registerDto))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already used by other user.");

        AppUser appUser = Mapper.mapRegisterDtoToAppUser(registerDto);

        appUserRepository.save(appUser);

        String token = JWTUtility.createToken(appUser.getUserName());

        UserDto userDto = Mapper.mapAppUserToUserDto(appUser, token);

        return ResponseEntity.ok( userDto );
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {

        AppUser appUser = appUserRepository.findAppUserByLoginProvider( loginDto.loginProvider );

        if (appUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);

        if (!appUser.getPassword().equals(loginDto.password))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);

        String token = JWTUtility.createToken(appUser.getUserName());

        UserDto userDto = Mapper.mapAppUserToUserDto(appUser, token);

        return ResponseEntity.ok( userDto );
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> verify(HttpServletRequest request) {

        try {
            String authorizationHeader = request.getHeader(AUTHORIZATION);

            String userName = JWTUtility.extractUserNameFromToken(authorizationHeader);

            AppUser appUser = appUserRepository.findAppUserByUserName(userName);

            return appUser != null ? ResponseEntity.ok(true) :
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }

    @GetMapping("/get-username-from-token")
    public ResponseEntity<String> getUsernameFromToken(HttpServletRequest request) {

        try {
            String userName = JWTUtility.extractUserNameFromToken( request.getHeader(AUTHORIZATION) );
            return ResponseEntity.ok( userName );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    /// validations
    private boolean checkUserNameIsTaken(RegisterDto registerDto) {
        return appUserRepository.findAppUserByLoginProvider(registerDto.userName) != null;
    }

    private boolean checkEmailUsedBefore(RegisterDto registerDto) {
        return appUserRepository.findAppUserByLoginProvider(registerDto.email) != null;
    }

}
