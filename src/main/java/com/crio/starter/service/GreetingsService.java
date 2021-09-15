package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingsService {

  private final GreetingsRepository greetingsRepository;

  public ResponseDto getMessage(long id) {
    return new ResponseDto(greetingsRepository.findById(id).getId());
  }

  private final AtomicLong counter = new AtomicLong();

  public ResponseDto postMeme(GreetingsEntity greetingsEntity) {
    greetingsEntity.setId(counter.incrementAndGet());
    greetingsRepository.save(greetingsEntity);
    return new ResponseDto(greetingsEntity.getId());
  }

  public List<GreetingsEntity> getMemes(){
    return greetingsRepository.findAll();
  }


  public GreetingsEntity findById(long id) {
    return greetingsRepository.findById(id);
  }
  public GreetingsEntity findByName(String name) {
    return greetingsRepository.findByName(name);
  }
  public GreetingsEntity findByUrl(String url) {
    return greetingsRepository.findByUrl(url);
  }
  public GreetingsEntity findByCaption(String caption) {
    return greetingsRepository.findByCaption(caption);
  }
}
