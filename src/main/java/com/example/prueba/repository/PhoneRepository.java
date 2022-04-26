package com.example.prueba.repository;
import com.example.prueba.model.PhoneModel;
import com.example.prueba.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<PhoneModel, Long> {

    @Override
    PhoneModel save(PhoneModel phone);
}
