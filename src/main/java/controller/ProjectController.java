package controller;

import dto.BoardDto;
import java.util.Vector;
import service.BoardService;
import service.TextToImageService;
import view.InputView;
import view.OutputView;

public class ProjectController {
    private final String filepath = "C:\\test\\board.txt"; ///파일 경로는 적절한 위치에 추가해주셔야 영속성 유지가 가능합니다.
    private BoardService boardService;
    private InputView inputView;
    private OutputView outputView;
    private TextToImageService textToImageService;

    public ProjectController() { //객체 생성
        this.boardService = new BoardService(filepath);
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.textToImageService = new TextToImageService();
    }

    public void run() 
    {
        while (true) {
            outputView.printBoard(boardService.getAllPosts()); //현재 존재하는 모든 게시글 출력

            outputView.printIndex(); // 선택지 출력
            int choice = inputView.getUserChoice(); //사용자가 메뉴 숫자 입력

            switch (choice) {
                case 1:
                    addPost();
                    break;
                case 2:
                    updatePost();
                    break;
                case 3:
                   convertToImage();
                   break;
                
                case 4:
                    deletePost();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("Error! 올바른 번호를 입력하세요.");
            }
        }
    }

    // 사용자가 새로운 게시글 추가 
    public void addPost() {
        System.out.println("새 게시글을 추가합니다.");
        BoardDto boardDto = inputView.getInput();
    
        if (boardDto != null) {
            boardService.addBoard(boardDto); // 메서드 이름 수정
            System.out.println("게시글이 추가되었습니다.");
        } else {
            System.out.println("게시글 추가에 실패했습니다.");
        }
    }
    
 

    

    // 사용자가 기존에 존재하던 게시글 수정
    public void updatePost() 
    {
        int index = inputView.getIndex()-1;
        if (index == -1) return;
    
        if (!boardService.exists(index)) // 해당 게시글이 존재하지 않을 경우
        {
            System.out.println(" Error! 해당 인덱스의 게시글이 존재하지 않습니다.");
            return;
        }
    
        // 사용자가 수정할 항목 선택 (제목 or 내용 or 둘 다)
        int updateChoice = inputView.getUpdateChoice();
    
        switch (updateChoice) {
            case 1:
                // 제목 수정
                String newTitle = inputView.getTitleInput();
                boardService.updatePostTitle(index, newTitle); // boardService의 메서드 활용
                System.out.println("제목이 수정되었습니다.");
                break;
            case 2:
                // 내용 수정
                String newContent = inputView.getContentInput();
                boardService.updatePostContent(index, newContent); //boardService의 메서드 활용
                System.out.println("내용이 수정되었습니다.");
                break;
            case 3:
                // 제목과 내용 모두 수정
                String updatedTitle = inputView.getTitleInput();
                String updatedContent = inputView.getContentInput();
                boardService.updatePost(index, updatedTitle, updatedContent); //boardService의 메서드 활용
                System.out.println("제목과 내용이 모두 수정되었습니다.");
                break;
            default:
                System.out.println("Error! 올바른 번호를 선택하세요.");
        }
    }
    // 사용자가 기존 게시글 삭제 요청
    public void deletePost() {
    int index = inputView.getIndex()-1; // 삭제할 게시글 인덱스 입력

    if (index == -1) {
        return; // 잘못된 인덱스 입력 시 메서드 종료
    }

    if (!boardService.exists(index)) {
        System.out.println("Error! 해당 인덱스의 게시글이 존재하지 않습니다.");
        return;
    }

    // 게시글이 존재할 경우 삭제 요청
    boardService.deleteBoard(index);
    System.out.println("게시글이 삭제되었습니다.");
    }


    public void convertToImage() 
    {
        int index = inputView.getAiIndex();
        Vector<BoardDto> temp =  boardService.getAllPosts();
        String content = temp.get(index - 1).getContent();
        textToImageService.convertToImage(content);
    }
}
