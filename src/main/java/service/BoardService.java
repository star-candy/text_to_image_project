package service;

import dto.BoardDto;
import java.util.Vector;
import model.CrudManager;
import model.FileIoManager;

import java.util.Vector;

public class BoardService {
    private final CrudManager crudManager;
    private final FileIoManager fileIoManager;

    public BoardService(String filePath) {
        this.crudManager = new CrudManager();
        this.fileIoManager = new FileIoManager(filePath);
        initialize();
    }

    // 서비스 시작 시 데이터베이스 로드
    private void initialize() {
        Vector<BoardDto> db = fileIoManager.loadDbFromFile();
        crudManager.setDb(db);
    }

    // 서비스 종료 시 데이터베이스 저장
    public void shutdown() {
        Vector<BoardDto> db = crudManager.getDb();
        fileIoManager.saveDbToFile(db);
    }

    // 게시글 추가
    public String addBoard(BoardDto boardDto) {
        if (boardDto == null) {
            return "Error! 유효하지 않은 게시글 데이터입니다.";
        }
        crudManager.requestCreate(boardDto);
        // 변경 사항 저장
        fileIoManager.saveDbToFile(crudManager.getDb());
        return "게시글이 추가되었습니다: " + boardDto.getTitle();
    }

    // 게시글 제목 수정
    public String updatePostTitle(int index, String newTitle) {
        try {
            BoardDto post = crudManager.requestRead(index);
            post.setTitle(newTitle);
            crudManager.requestUpdate(index, post);
            // 변경 사항 저장
            fileIoManager.saveDbToFile(crudManager.getDb());
            return "제목이 수정되었습니다.";
        } catch (IndexOutOfBoundsException e) {
            return "Error! " + e.getMessage();
        }
    }

    // 게시글 내용 수정
    public String updatePostContent(int index, String newContent) {
        try {
            BoardDto post = crudManager.requestRead(index);
            post.setContent(newContent);
            crudManager.requestUpdate(index, post);
            // 변경 사항 저장
            fileIoManager.saveDbToFile(crudManager.getDb());
            return "내용이 수정되었습니다.";
        } catch (IndexOutOfBoundsException e) {
            return "Error! " + e.getMessage();
        }
    }

    // 게시글 제목과 내용 모두 수정
    public String updatePost(int index, String newTitle, String newContent) {
        try {
            BoardDto post = crudManager.requestRead(index);
            post.setTitle(newTitle);
            post.setContent(newContent);
            crudManager.requestUpdate(index, post);
            // 변경 사항 저장
            fileIoManager.saveDbToFile(crudManager.getDb());
            return "제목과 내용이 모두 수정되었습니다.";
        } catch (IndexOutOfBoundsException e) {
            return "Error! " + e.getMessage();
        }
    }

    // 게시글 삭제 요청
    public String deleteBoard(int index) {
        try {
            crudManager.requestDelete(index);
            // 변경 사항 저장
            fileIoManager.saveDbToFile(crudManager.getDb());
            return "게시글이 삭제되었습니다.";
        } catch (IndexOutOfBoundsException e) {
            return "Error! " + e.getMessage();
        }
    }

    // 게시글이 존재하는지 확인
    public boolean exists(int index) {
        try {
            crudManager.requestRead(index);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    // 모든 게시글 반환
    public Vector<BoardDto> getAllPosts() {
        return crudManager.requestAll();
    }
}
