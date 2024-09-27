package org.example.model;

import java.util.Vector;
import org.example.dto.BoardDTO;
import org.example.repository.BoardDb;

public class CrudManager {
    void requestCreate(BoardDTO board) {
        BoardDb.createBoard(board);
    }

    BoardDTO requestRead(int index) {
        return BoardDb.readBoard(index);
    }

    void requestUpdate(int index, BoardDTO board) {
        BoardDb.updateBoard(index, board);
    }

    void requestDelete(int index) {
        BoardDb.deleteBoard(index);
    }

    Vector<BoardDTO> requestFull() {
        return BoardDb.fullBoard();
    }


}
