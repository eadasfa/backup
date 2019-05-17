package spittr.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spittr.entity.Spitter;

@Repository
public interface SpitterRepository extends JpaRepository<Spitter, String>{
	//以下两种
//	@Query(value="select * from Spitter s where binary s.username  = :username",
//	nativeQuery=true) 
//	Spitter findByUserNameBinary(@Param(value = "username")  String username);
	@Query(value="select * from Spitter s where binary s.username  = ?1",
	nativeQuery=true) 
	Spitter findByUserNameBinary(String username);
}
