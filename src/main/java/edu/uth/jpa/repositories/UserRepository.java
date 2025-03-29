package edu.uth.jpa.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.uth.jpa.models.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    //Delete from Product where id = @id
//    boolean save(User user){
//        return true;

}
