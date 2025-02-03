package model;

import dto.BoardDto;

import java.util.Vector;

public class CrudManager {
    private final Vector<BoardDto> db;

    public CrudManager() {
        db = new Vector<>();
    }

    // 데이터베이스 설정
    public void setDb(Vector<BoardDto> db) {
        this.db.clear();
        this.db.addAll(db);
    }

    // 데이터베이스 가져오기
    public Vector<BoardDto> getDb() {
        return new Vector<>(db); // 외부에서 직접 수정할 수 없도록 복사본 반환
    }

    // 게시글 생성
    public void requestCreate(BoardDto boardDto) {
        db.add(boardDto);
    }

    // 게시글 조회
    public BoardDto requestRead(int index) {
        try {
            return db.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("잘못된 인덱스입니다: " + index);
        }
    }

    // 게시글 수정
    public void requestUpdate(int index, BoardDto boardDto) {
        try {
            db.set(index, boardDto);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("잘못된 인덱스입니다: " + index);
        }
    }

    // 게시글 삭제
    public void requestDelete(int index) {
        try {
            db.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("잘못된 인덱스입니다: " + index);
        }
    }

    // 모든 게시글 반환
    public Vector<BoardDto> requestAll() {
        return new Vector<>(db); // 외부에서 직접 수정할 수 없도록 복사본 반환
    }
}
