package com.exampletokener.demo.Controll;

import com.exampletokener.demo.Model.IdToken;
import com.exampletokener.demo.Repo.IdTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/data")
public class IdTokencontroller {

        @Autowired
        private IdTokenRepo idTokenRepo;
    @RequestMapping(path = "/savetoken")
    public IdToken addToken(IdToken idtoken)
    {
        return  idTokenRepo.save(idtoken);

    }
    @GetMapping("/users/{id}")
    public Optional<IdToken> getNoteById(@PathVariable(value = "id") int id)
    {
        return idTokenRepo.findById(id);
    }
}
