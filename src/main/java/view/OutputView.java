package view;

import dto.BoardDto;
import java.util.Vector;
import model.CrudManager;

public class OutputView {
    private CrudManager crudManager;

    // 생성자에서 CrudManager 인스턴스 초기화
    public OutputView() {
    }

    // 현재까지 저장되어 있는 게시글 모두 출력
    public void printBoard(Vector<BoardDto> boardList) {

        System.out.println("=== 게시판 목록 ===");

        if (boardList.isEmpty()) {
            System.out.println("아직 게시글이 없습니다. 게시글을 입력해주세요.");
            return;
        }

        // 게시글 출력
        for (BoardDto board : boardList) {
            System.out.println("글ID: " + board.getIndex() +" | 제목: " + board.getTitle() + " | 내용: " + board.getContent());
        }
    }

    // 사용자에게 게시판 기능 메뉴를 출력
    public void printIndex() {
        System.out.println("\n=== 메뉴 ===");
        System.out.println("1. 새 글쓰기");
        System.out.println("2. 수정하기");
        System.out.println("3. 이미지 변환하기");
        System.out.println("4. 글 삭제하기");
        System.out.println("5. 종료하기");
        System.out.print("원하는 작업의 번호를 입력하세요: ");
    }
}
