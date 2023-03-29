package mx.edu.utez.sda.u2p1.u2p1.repositories;

import mx.edu.utez.sda.u2p1.u2p1.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
