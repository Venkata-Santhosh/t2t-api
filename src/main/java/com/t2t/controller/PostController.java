/**
 *  This is Restful service for Posting items
 */
package com.t2t.controller;

import com.t2t.constants.Constants;
import com.t2t.entities.Post;
import com.t2t.services.PostService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {
    private PostService service;

    public PostController(PostService service){
        this.service = service;
    }

    // returns list of posted items that are posted
    @RequestMapping(method = RequestMethod.GET)
    public List<Post> posts() {
        return service.findAllPosts();
    }

    // used to create new post
    @RequestMapping(method = RequestMethod.POST)
    public Post create(@RequestBody Post post) {
        return service.createPost(post);
    }

    //update created post using id
    @RequestMapping(method = RequestMethod.PUT, value = Constants.ID)
    public Post update(@PathVariable("id") String id, @RequestBody Post post) {
        return service.updatePost(id, post);
    }

    //delete created post using id
    @RequestMapping(method = RequestMethod.DELETE, value = Constants.ID)
    public void delete(@PathVariable("id") String id) {
        service.deletePost(id);
    }
}
