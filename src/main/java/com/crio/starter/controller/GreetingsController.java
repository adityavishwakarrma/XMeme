package com.crio.starter.controller;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
  public ResponseDto sayHello(@RequestParam long id) {
    return greetingsService.getMessage(id);
  }


  @PostMapping("/memes/")
  public ResponseEntity<ResponseDto> postMeme(@RequestBody GreetingsEntity greetingsEntity) {

    String name = greetingsEntity.getName();
    String url =  greetingsEntity.getUrl();
    String caption = greetingsEntity.getCaption();

    if( name==null && url==null && caption==null){
      return ResponseEntity.status(400).body(null);
    }

    if (greetingsService.findByName(name) != null || greetingsService.findByUrl(url) != null || greetingsService.findByCaption(caption) != null) {
      return ResponseEntity.status(409).body(null);     //duplicate data sends 409 status
    } else {
    ResponseDto response = greetingsService.postMeme(greetingsEntity);
    return ResponseEntity.ok().body(response);          //post a meme
    }
  }

  @GetMapping("/memes/")
  public ResponseEntity<List<GreetingsEntity>> getMemes() {
    List<GreetingsEntity> greetingsEntities = greetingsService.getMemes();

    if (greetingsEntities == null) {
      return ResponseEntity.status(200).body(null);   //When run with empty database, get calls should return success, and response should be empty

    } else if(greetingsEntities.size() <=100) {       //Insert 50 MEMEs and try accessing them to confirm that all of them are returned back
      return ResponseEntity.ok(greetingsEntities);

    } else {                                          //Post more than 100 MEME, make a GET call and ensure that it returns only latest 100 MEME
      List<GreetingsEntity> list = new ArrayList<>();
      for(int i=0; i<100; i++){
        list.add(greetingsEntities.get(i));
      }
      return ResponseEntity.ok(greetingsEntities);
    }

  }

  @GetMapping("/memes/{id}")
  public ResponseEntity<GreetingsEntity> getMeme(@PathVariable("id") long id) {
    GreetingsEntity greetingsEntity = greetingsService.findById(id);
    if(greetingsEntity != null ) {
      return ResponseEntity.ok(greetingsEntity);
    } else {
      return ResponseEntity.status(404).body(null);
    }
 
  }


}
