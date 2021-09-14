package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsService {

  private final GreetingsRepository greetingsRepository;

  public ResponseDto getMessage(Long id) {
    return new ResponseDto(id);
  }

  private final AtomicLong counter = new AtomicLong();
  
  public ResponseDto postMeme(GreetingsEntity greetingsEntity) {
    greetingsEntity.setId(counter.incrementAndGet());
    greetingsRepository.save(greetingsEntity);
    return new ResponseDto(greetingsEntity.getId());
  }

  public List<GreetingsEntity> getMemes(){
    List<GreetingsEntity> greetingsEntities = greetingsRepository.findAll();
    if(greetingsEntities.size() <=100){
      return greetingsEntities;             //TEST 7
    } else {
      List<GreetingsEntity> list = new ArrayList<>();
      for(int i=0; i<100; i++){             //TEST 8 FAIL
        list.add(greetingsEntities.get(i));
      }
      return list;
    }
  }


  public GreetingsEntity findById(long id){
    return greetingsRepository.findById(id);
  }
  public GreetingsEntity findByName(String name){
    return greetingsRepository.findByName(name);
  }
  public GreetingsEntity findByUrl(String url){
    return greetingsRepository.findByUrl(url);
  }
  public GreetingsEntity findByCaption(String caption){
    return greetingsRepository.findByCaption(caption);
  }
}
