package com.crio.starter.controller;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsController {

  private final GreetingsService greetingsService;

  @GetMapping("/say-hello")
  public ResponseEntity<ResponseDto> sayHello(@RequestParam Long messageId) {
    ResponseDto responseDto = greetingsService.getMessage(messageId);
    return ResponseEntity.ok(responseDto);
  }
  
  @PostMapping("/memes/")
  public ResponseEntity<ResponseDto> postMeme(@RequestBody GreetingsEntity greetingsEntity) {

    if(greetingsService.getMemeByUrl(greetingsEntity.getUrl()) != null &&
       greetingsService.getMemeByCaption(greetingsEntity.getCaption()) != null &&
       greetingsService.getMemeByName(greetingsEntity.getName()) != null ) {

      return ResponseEntity.status(409).body(new ResponseDto());  //for same payload  //error 3

    } else if(greetingsEntity == null ||greetingsEntity.getName() == null || greetingsEntity.getUrl() == null || greetingsEntity.getCaption() == null) {

      return ResponseEntity.status(200).body(new ResponseDto());   //error 2

    } else
    {

      ResponseDto responseDto = greetingsService.postMeme(greetingsEntity);
      return ResponseEntity.ok(responseDto);                     //post the meme
    }
    
  }

  @GetMapping("/memes/")
  public ResponseEntity<List<GreetingsEntity>> getMemes(){
    List<GreetingsEntity> greetingsEntities = greetingsService.getMemes();
    return ResponseEntity.ok(greetingsEntities);
  }
  
  @GetMapping("/memes/{id}")//error 1
  public ResponseEntity<GreetingsEntity> getMemeById(@PathVariable("id") Long id) {
    GreetingsEntity greetingsEntity = greetingsService.getMemeById(id);

    if(greetingsEntity != null) {
    return ResponseEntity.ok(greetingsEntity);
    } else {
      return ResponseEntity.status(404).body(null);
    }

  }
}
