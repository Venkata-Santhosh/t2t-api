/**
 * Post entity class used to store post information
 * Post - Category one on one relationship
 */
package com.t2t.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@JsonIgnoreProperties
public class Post implements Comparable<Post>{
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private String imageURL;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int compareTo(Post o) {
        return this.getId().compareTo(o.id);
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURl(String imageURL) {
        this.imageURL = imageURL;
    }
}
