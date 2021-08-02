package org.assessment.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.assessment.data.Post;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PostRepository implements PanacheRepositoryBase<Post, Integer> {

    public List<Post> findAllPosts() {
        return findAll().list();
    }

    public List<Post> findPostsByTitle(String title) {
        return find("title", title).list();
    }

}