package org.assessment;

import org.assessment.data.Post;
import org.assessment.data.repository.PostRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/post")
public class PostResource {

    @Inject
    EntityManager entityManager;

    @Inject
    PostRepository postRepository;

    // Get all post
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.NEVER)
    public List<Post> getList() {
        return Post.listAll();
    }

    // Get post by title
    @Path("/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.NEVER)
    public List<Post> getPostByTitle(@PathParam("title") String title) {
        return postRepository.findPostsByTitle(title);
    }

    // Create post
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Post savePost(Post post) {
        post.persist();
        return post;
    }

    // Update post by id
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Post updatePost(@PathParam("id") Integer id, Post post) {
        Post findPost = Post.findById(id);

        if(findPost == null)
            return null;

        post.setId(id);
        entityManager.merge(post);
        return post;
    }

    // Delete post by id
    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public boolean deletePost(@PathParam("id") Integer id) {     
        Post post = Post.findById(id);

        if(post == null)
            return false;

        post.delete();        
        return true;
    }
}