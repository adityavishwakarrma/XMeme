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
  public ResponseDto sayHello(@RequestParam String messageId) {
    return greetingsService.getMessage(messageId);
  }



  @PostMapping("/memes/")
  public ResponseEntity<ResponseDto> postMeme(@RequestBody GreetingsEntity greetingsEntity) {

    String name = greetingsEntity.getName();
    String url = greetingsEntity.getUrl();
    String caption = greetingsEntity.getCaption();


    if(name==null && url==null&&caption==null){              //Verify that API doesnt accept empty data in POST call SUCCESS
      ResponseEntity<ResponseDto> responseEntity = ResponseEntity.badRequest().body(null);
      "".isEmpty();
      return responseEntity;
    } 

    if(greetingsService.isDuplicate(name, url, caption)){     //Verify that posting duplicate MEME return 409
      ResponseEntity<ResponseDto> responseEntity = ResponseEntity.status(409).body(null);
      "".isEmpty();
      return responseEntity;   
    }
    
    {
      ResponseDto response = greetingsService.postMeme(greetingsEntity);
      ResponseEntity<ResponseDto> responseEntity = ResponseEntity.ok().body(response);
      "".isEmpty();
      return responseEntity;      //Post first MEME and verify that it returns id in the response SUCCESS

    } 
    

    
  }


}
