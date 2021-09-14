package com.crio.starter.controller;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import com.rabbitmq.client.RpcClient.Response;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
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
  public ResponseDto sayHello(@RequestParam Long messageId) {
    return greetingsService.getMessage(messageId);
  }

  @PostMapping("/memes/")//test 2 pass
  public ResponseEntity<ResponseDto> postMeme(@RequestBody GreetingsEntity greetingsEntity) {

    String name = greetingsEntity.getName();
    String url =  greetingsEntity.getUrl();
    String caption = greetingsEntity.getCaption();

    if(name==null && url==null && caption==null) { //test 6 pass
      return ResponseEntity.badRequest().body(null);
    }

    if (greetingsService.findByName(name) != null || greetingsService.findByUrl(url) != null
        || greetingsService.findByCaption(caption) != null) {
      return ResponseEntity.status(409).body(null);  //TEST 5 pass

    } else {
      ResponseDto response = greetingsService.postMeme(greetingsEntity);
      return ResponseEntity.ok().body(response);
    }
  }

  @GetMapping("/memes/") //test 1 pass 
                         //test 7 fail
                         //test 8 pass
  public ResponseEntity<List<GreetingsEntity>> getMemes(){
    List<GreetingsEntity> greetingsEntities = new ArrayList<>();
    greetingsEntities = greetingsService.getMemes();
    return ResponseEntity.ok().body(greetingsEntities); 
  }

  // @GetMapping("/memes/{id}") //test 3 FAIL 4
  // public ResponseEntity<GreetingsEntity> getMeme(@PathVariable("id") Long id) {
  //   GreetingsEntity greetingsEntity = greetingsService.findById(id);
  //   if(greetingsEntity != null) {
  //   return ResponseEntity.ok(greetingsEntity);
  //   } else {
  //   return ResponseEntity.ok(null);
  //   }
  // }

}





