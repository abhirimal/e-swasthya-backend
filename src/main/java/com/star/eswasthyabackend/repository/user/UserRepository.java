package com.star.eswasthyabackend.repository.user;

import com.star.eswasthyabackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "select * from users " +
            "where email=?1")
    User loadUserByUsername(String email);

}