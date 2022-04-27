package cl.usuarios.repository;
import cl.usuarios.model.PhoneModel;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<PhoneModel, Long> {

    @Override
    PhoneModel save(PhoneModel phone);
}
