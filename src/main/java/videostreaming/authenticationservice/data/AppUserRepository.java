package videostreaming.authenticationservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import videostreaming.authenticationservice.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

}
