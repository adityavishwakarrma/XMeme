package com.crio.starter.repository;

import java.util.List;

import com.crio.starter.data.GreetingsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GreetingsRepository extends MongoRepository<GreetingsEntity, Long> {
  <S extends GreetingsEntity> S save(S entity);
  List<GreetingsEntity> findAll();
  GreetingsEntity findById(long id);
  GreetingsEntity findByName(String name);
  GreetingsEntity findByUrl(String url); 
  GreetingsEntity findByCaption(String caption);
}
