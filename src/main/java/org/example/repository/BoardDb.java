package org.example.repository;
import java.util.Vector;
import org.example.dto.BoardDTO;

public class BoardDb {
    private static Vector<BoardDTO> dataBase;

    //생성자 통한 초기화는 로컬 파일에서 dataBase 불러오는 기능 최적화 위해 사용 / 올바른 방식인지 생각해볼것
    BoardDb(Vector<BoardDTO> initializeDb) {
        dataBase = initializeDb;
    }

    public static void createBoard(BoardDTO board) {
        dataBase.add(board);
    }

    public static BoardDTO readBoard(int index) {
        return dataBase.get(index);
    }

    public static void updateBoard(int index, BoardDTO board) {
        dataBase.set(index, board);
    }

    public static void deleteBoard(int index) {
        dataBase.remove(index);
    }

    public static Vector<BoardDTO> fullBoard() {
        return dataBase;
    }

}
