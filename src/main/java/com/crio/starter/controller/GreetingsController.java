package com.crio.starter.controller;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsController {

  private final GreetingsService greetingsService;

  @GetMapping("/say-hello")
  public ResponseDto sayHello(@RequestParam Long messageId) {
    return greetingsService.getMessage(messageId);
  }

  

  @PostMapping("/memes/")
  public ResponseEntity<ResponseDto> postMeme(@RequestBody GreetingsEntity greetingsEntity) {

    if( greetingsEntity == null || greetingsEntity.getName() == null || greetingsEntity.getUrl() == null || greetingsEntity.getCaption() == null) {
      return ResponseEntity.badRequest().body(null);
    } else {
      ResponseDto response = greetingsService.postMeme(greetingsEntity);
      return ResponseEntity.ok().body(response);
    }
  }

}


