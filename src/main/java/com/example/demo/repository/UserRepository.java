package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SiteUser;

/**
 * SiteUser(Entity)クラスのリポジトリクラス.
 */
@Repository
public interface UserRepository extends JpaRepository<SiteUser, String> {

	@Query("SELECT COUNT(e) FROM SiteUser e WHERE e.username = :username")
	long countByUsername(@Param("username") String username);
}
