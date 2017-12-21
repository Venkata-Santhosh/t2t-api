/**
 * Category class used to store category information
 * Entity class
 */
package com.t2t.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties
public class Category implements Comparable<Category>{
    @Id
    @GeneratedValue
    private Long id;
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    //helps to compare two object by spring jpa
    @Override
    public int compareTo(Category o) {
        return this.getId().compareTo(o.id);
    }
}
