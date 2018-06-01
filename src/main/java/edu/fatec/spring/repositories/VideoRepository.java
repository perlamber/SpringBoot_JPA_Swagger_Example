package edu.fatec.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fatec.spring.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
	Video findById(Long id);
	List<Video> findAll();
}
