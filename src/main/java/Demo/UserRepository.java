package Demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // Custom repository method to find a user by email
    Optional<User> findByEmail(String email);

    // Custom repository method to find a user by username
    Optional<User> findByUsername(String username);
}