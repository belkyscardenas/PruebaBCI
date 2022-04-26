package com.example.prueba.repository;
import com.example.prueba.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {

    @Override
    UserModel save(UserModel user);
}
