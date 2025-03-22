package io.silver.service;

import io.silver.data.Board;
import io.silver.repository.BoardRepository;

public class BoardService {

    private BoardRepository boardRepository = new BoardRepository();

    public int addBoard(String name, String desc) {
        return boardRepository.save(name, name);
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

}
