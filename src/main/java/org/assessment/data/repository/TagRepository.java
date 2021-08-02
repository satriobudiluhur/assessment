package org.assessment.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.assessment.data.Tag;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TagRepository implements PanacheRepositoryBase<Tag, Integer> {

    public List<Tag> findAllTag() {
        return findAll().list();
    }

    public List<Tag> findTagsByLabel(String label) {
        return find("label", label).list();
    }

}