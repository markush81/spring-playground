package sample.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.persistence.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
