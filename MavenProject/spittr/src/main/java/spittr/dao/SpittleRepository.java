package spittr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spittr.entity.Spittle;

@Repository
public interface SpittleRepository extends JpaRepository<Spittle, Long>{

}
