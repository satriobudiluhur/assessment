package org.assessment;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.assessment.data.Tag;
import org.assessment.data.repository.TagRepository;

@Path("/tag")
public class TagResource {

    @Inject
    TagRepository tagRepository;

    // Get all tag
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.NEVER)
    public List<Tag> getList() {
        return Tag.listAll();
    }

    // Get tag by label
    @Path("/{tag_label}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.NEVER)
    public List<Tag> getList(@PathParam("tag_label") String label) {
        return tagRepository.findTagsByLabel(label);
    }

}