package io.silver.service;

import io.silver.data.Board;
import io.silver.data.Post;
import io.silver.repository.PostRepository;

public class PostService {

    private PostRepository postRepository = new PostRepository();
    private BoardService boardService = new BoardService();

    public int addPost(String title, String body) {
        return postRepository.save(title, body);
    }

    public void removePostById(int removeId) {
        postRepository.removeById(removeId);
    }

    public void editPostById(int editId, String title, String body) {
        postRepository.update(editId, title, body);
    }

    public Post viewPostById(int viewId) {
        return postRepository.getById(viewId);
    }

    public boolean isValidBoard(int boardId) {
        return boardService.validTestById(boardId);
    }

    public void addOnBoard(int boardId, int postId) {
        boardService.addPostsOnBoard(boardId, viewPostById(postId));
    }
}
