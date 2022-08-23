package com.annaburnaeva.fitnessClub.repository;

import com.annaburnaeva.fitnessClub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

}
