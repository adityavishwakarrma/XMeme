package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;
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
    if(findByName(name)!=null && findByUrl(url)!=null && findByCaption(caption)!=null)
    return true;
    else return false;
  }

  public GreetingsEntity findByExtId(String extId){
    return greetingsRepository.findByExtId(extId);
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
