package com.exampletokener.demo.Repo;

import com.exampletokener.demo.Model.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepo extends CrudRepository<Token, Integer> {
}
