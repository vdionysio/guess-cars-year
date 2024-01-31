package com.dionysio.guesscarsyear.models.repositories;

import com.dionysio.guesscarsyear.models.entities.Guess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuessRepository extends JpaRepository<Guess, Long> {

}
