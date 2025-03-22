package io.silver.repository;

import io.silver.data.Post;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private int sequence = 0;
    private List<Post> postList = new ArrayList<>();

    public int save(String title, String body) {

        Post newPost = new Post(++sequence, title, body);
        postList.add(newPost);

        return newPost.getId();
    }

    public void removeById(int removeId) {

        Post findPost = postList.get(removeId - 1);

        if(findPost != null) {
            postList.set(removeId, null);
        }


    }

    public void update(int editId, String title, String body) {

        Post findPost = postList.get(editId - 1);

        findPost.setTitle(title);
        findPost.setBody(body);
        findPost.setUpdatedAt(LocalDate.now());

    }

    public Post getById(int viewId) {

        Post findPost = postList.get(viewId - 1);

        return findPost;
    }

}
