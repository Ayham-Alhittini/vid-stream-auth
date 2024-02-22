package videostreaming.authenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import videostreaming.authenticationservice.data.AppUserRepository;
import videostreaming.authenticationservice.models.AppUser;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AuthController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/test")
    public AppUser test() {

        AppUser user = new AppUser("Ayham-Alhettini", "Ayham", "ayham.hittini268@gmail.com", "Pa$$w0rd");

        appUserRepository.save(user);

        return user;
    }




}
