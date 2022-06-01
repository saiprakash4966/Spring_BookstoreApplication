package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, Integer>
{
    @Query(value = "select * from user_registration_table where email = :email", nativeQuery = true)
    UserRegistrationData getUserByEmailId(String email);

    UserRegistrationData findByEmailAndPassword(String email, String password);
}
