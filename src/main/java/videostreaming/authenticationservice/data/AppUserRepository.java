package videostreaming.authenticationservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import videostreaming.authenticationservice.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    @Query(value = "SELECT * FROM users WHERE user_name = ?1 OR email = ?1", nativeQuery = true)
    AppUser findAppUserByLoginProvider(String loginProvider);

    AppUser findAppUserByUserName(String username);
}
