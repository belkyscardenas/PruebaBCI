package cl.usuarios.repository;
import cl.usuarios.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {

    @Override
    UserModel save(UserModel user);
}
