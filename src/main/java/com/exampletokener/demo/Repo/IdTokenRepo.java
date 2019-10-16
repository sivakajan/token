package com.exampletokener.demo.Repo;

import com.exampletokener.demo.Model.IdToken;
import org.springframework.data.repository.CrudRepository;

public interface IdTokenRepo extends CrudRepository<IdToken,Integer> {
}
