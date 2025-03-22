package io.silver.repository;

import io.silver.data.Board;
import io.silver.data.Post;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BoardRepository {

    private int sequence = 0;
    private List<Board> boardList = new ArrayList<>();

    public int save(String name, String desc) {

        Board newBoard = new Board(++sequence, name, desc);
        boardList.add(newBoard);

        return newBoard.getId();
    }

    public void removeById(int removeId) {

        Board findBoard = boardList.get(removeId - 1);

        if(findBoard != null) {
            boardList.set(removeId -1, null);
        }


    }

    public void update(int editId, String name, String desc) {

        Board findBoard = boardList.get(editId - 1);

        findBoard.setBoardName(name);
        findBoard.setDescription(desc);
        findBoard.setUpdatedAt(LocalDate.now());

    }

    public Board getById(int viewId) {

        Board findBoard = boardList.get(viewId - 1);

        return findBoard;
    }

    public void exist(int boardId) {

        if(boardList.get(boardId-1) != null){
            return;
        } else {
            throw new NoSuchElementException("아직 게시판이 존재하지 않습니다. 게시판을 먼저 생성해주세요 !");
        }

    }

    public void addPostsById(int boardId, Post newPost) {
        boardList.get(boardId-1).getPostList().add(newPost);
    }
}
