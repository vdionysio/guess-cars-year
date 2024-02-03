package com.dionysio.guesscarsyear.models.repositories;

import com.dionysio.guesscarsyear.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
