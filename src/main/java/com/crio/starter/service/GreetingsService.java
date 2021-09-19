package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsService {

  private final GreetingsRepository greetingsRepository;
  private final AtomicInteger counter = new AtomicInteger();

  public ResponseDto getMessage(String id) {
    return new ResponseDto(greetingsRepository.findByExtId(id).getExtId());
  }


  public ResponseDto postMeme(GreetingsEntity greetingsEntity) {
    String extId = Integer.toString(counter.incrementAndGet());
    greetingsEntity.setExtId(extId);
    greetingsRepository.save(greetingsEntity);
    return new ResponseDto(extId);
  }

  public boolean isDuplicate(String name, String url, String caption) {
    List<GreetingsEntity> greetingsEntities = greetingsRepository.findAll();
    for(GreetingsEntity greetingsEntity : greetingsEntities){
      if(greetingsEntity.getName().equals(name) && greetingsEntity.getUrl().equals(url)
          && greetingsEntity.getCaption().equals(caption)) {
        return true;
      }
    }
    return false;
  }

  public List<GreetingsEntity> getMemes(){
    List<GreetingsEntity> greetingsEntities = new ArrayList<>(greetingsRepository.findAll());
    Collections.reverse(greetingsEntities);   //reverse
    int size = greetingsEntities.size();
    // get
    // When run with empty database, get calls should return success, and response should be empty
    if(size == 0){
      return new ArrayList<>();
    } else if(size <= 100){
      return greetingsEntities;  //Insert 50 MEMEs and try accessing them to confirm that all of them are returned back  
    } else {
      List<GreetingsEntity> greetingsEntities2 = greetingsEntities.subList(0, 100);
      return greetingsEntities2;  //Post more than 100 MEME, make a GET call and ensure that it returns only latest 100 MEME
    }
    
  }



}
