/**
 * Category Repository
 * Spring JPA - provides predefined DML functions to deal with database
 */
package com.t2t.jpa;

import com.t2t.entities.Category;
import org.springframework.data.repository.Repository;

public interface CategoryRepository extends Repository<Category,Long> {
    //spring jpa generate query which selects category based on category name
    //and returns matched Category
    Category findCategoryByCategory(String category);

}
