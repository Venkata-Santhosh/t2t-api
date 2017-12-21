/**
 * UserRepository
 * Spring JPA - provides predefined DML functions to deal with database
 */
package com.t2t.jpa;

import com.t2t.entities.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<AppUser,Integer> {
    //finds user by using his name
    AppUser findByUsername(String name);
    //creates new user
    AppUser save(AppUser post);
    //returns all user exits in application
    List<AppUser> findAll();
}