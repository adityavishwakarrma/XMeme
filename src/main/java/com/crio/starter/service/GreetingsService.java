package com.crio.starter.service;

import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingsService {

  private final GreetingsRepository greetingsRepository;

  public ResponseDto getMessage(long id) {
    return new ResponseDto(greetingsRepository.findById(id).getId());
  }
}
