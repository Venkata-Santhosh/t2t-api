/**
 * POST Repository
 * Spring JPA - provides predefined DML functions to deal with database
 */
package com.t2t.jpa;

import com.t2t.entities.Post;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends Repository<Post,Long> {
    //returns all posts present in database
    List<Post> findAll();
    //resturns post that matched with id
    Optional<Post> findOne(Long id);
    //deletes post
    void delete(Post post);
    //used to save post in database
    Post save(Post post);
}
