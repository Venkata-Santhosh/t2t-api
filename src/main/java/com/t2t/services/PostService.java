package com.t2t.services;

import com.t2t.entities.Category;
import com.t2t.entities.Post;
import com.t2t.exceptions.NotFoundException;
import com.t2t.jpa.CategoryRepository;
import com.t2t.jpa.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository repository;
    @Autowired
    CategoryRepository repositoryCat;

    public List<Post> findAllPosts() {
        return repository.findAll();
    }

    public Post createPost(Post post) {
        Category category = repositoryCat.findCategoryByCategory(post.getCategory().getCategory());
        if(category!=null){
            post.setCategory(category);
        }
        return  repository.save(post);
    }

    @Transactional
    public Post updatePost(String id,Post post) {
        Long postId = Long.parseLong(id);
        Post existingPost = repository.findOne(postId)
                .orElseThrow(() -> new NotFoundException("Post " + id + " does not exist"));
        existingPost.setTitle(post.getTitle());
        existingPost.setDescription(post.getDescription());
        return repository.save(existingPost);
    }

    @Transactional
    public void deletePost(String id) {
        Long postId = Long.parseLong(id);
        Post existing = repository.findOne(postId)
                .orElseThrow(() -> new NotFoundException("Post" + id + " does not exist"));
        repository.delete(existing);
    }

}
