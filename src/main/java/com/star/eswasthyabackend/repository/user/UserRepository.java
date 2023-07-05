package com.star.eswasthyabackend.repository.user;

import com.star.eswasthyabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.net.Inet4Address;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "select * from users " +
            "where email=?1")
    User loadUserByUsername(String email);

    @Query(nativeQuery = true, value = "select count(*)\n" +
            "from users")
    Integer countUser();

    @Query(nativeQuery = true, value = "select role\n" +
            "from users\n" +
            "where email = ?1")
    Object loadRoleByUserName(String username);
}
