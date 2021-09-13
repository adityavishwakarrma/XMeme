package com.crio.starter.repository;

import com.crio.starter.data.GreetingsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GreetingsRepository extends MongoRepository<GreetingsEntity, Long> {
  GreetingsEntity findByExtId(Long extId);
  GreetingsEntity findByName(String name);
  GreetingsEntity findByUrl(String url); 
  GreetingsEntity findByCaption(String caption);
}
