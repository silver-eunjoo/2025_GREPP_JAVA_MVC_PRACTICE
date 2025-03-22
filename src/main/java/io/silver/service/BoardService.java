package io.silver.service;

import io.silver.data.Board;
import io.silver.data.Post;
import io.silver.repository.BoardRepository;

public class BoardService {

    private BoardRepository boardRepository = new BoardRepository();

    public int addBoard(String name, String desc) {
        return boardRepository.save(name, desc);
    }

    public void removeBoardById(int removeId) {
        boardRepository.removeById(removeId);
    }

    public void editBoardById(int editId, String name, String desc) {
        boardRepository.update(editId, name, desc);
    }

    public Board viewBoardById(int viewId) {
        return boardRepository.getById(viewId);
    }

    public boolean validTestById(int boardId) {

        try{
            boardRepository.exist(boardId);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public void addPostsOnBoard(int boardId, Post newPost) {
        boardRepository.addPostsById(boardId, newPost);
    }

}
