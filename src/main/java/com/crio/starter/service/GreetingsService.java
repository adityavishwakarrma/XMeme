package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingsService {

  private final GreetingsRepository greetingsRepository;

  public ResponseDto getMessage(long id) {
    return new ResponseDto(greetingsRepository.findByExtId(id).getExtId());
  }


  private final AtomicLong counter = new AtomicLong();

  public ResponseDto postMeme(GreetingsEntity greetingsEntity){
    greetingsEntity.setExtId(counter.incrementAndGet());

    greetingsRepository.save(greetingsEntity);

    return new ResponseDto(greetingsEntity.getExtId());
  }


  public List<GreetingsEntity> getMemes(){
    List<GreetingsEntity> greetingsEntities = new ArrayList<>();

    greetingsEntities = greetingsRepository.findAll();

    if(greetingsEntities.size() <= 100) {
      return greetingsEntities;
    } else {
      List<GreetingsEntity> list = new ArrayList<>();

      for(int i=0; i<100; i++) {
        list.add(greetingsEntities.get(i));
      }
      return list;
    }
  }


  public GreetingsEntity getMemeById(Long ExtId){
    return greetingsRepository.findByExtId(ExtId);
  }

  
  public GreetingsEntity getMemeByUrl(String url){
    return greetingsRepository.findByUrl(url);
  }

  public GreetingsEntity getMemeByCaption(String caption){
    return greetingsRepository.findByCaption(caption);
  }
}
