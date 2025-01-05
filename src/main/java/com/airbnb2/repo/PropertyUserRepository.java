package com.airbnb2.repo;

import com.airbnb2.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {
   //correctly named method based on spring Data JPA conventions
    Optional<PropertyUser> findByUserName(String userName);
}


