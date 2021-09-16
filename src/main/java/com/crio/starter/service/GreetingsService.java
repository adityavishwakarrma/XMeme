package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

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





}
