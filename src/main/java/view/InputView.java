package view;

import dto.BoardDto;
import java.util.InputMismatchException;
import java.util.Scanner;

//사용자의 입력을 받는건 여기서 처리
public class InputView {
    private Scanner scanner = new Scanner(System.in);

   
    public int getUserChoice() // 사용자가 메뉴 중 하나 선택
    {
        while (true) {

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Error! 숫자를 입력해야 합니다. 다시 시도해주세요.");
                scanner.nextLine(); 
            }
        }
    }
    



    //인덱스, 제목, 내용은 한번에 입력 받아도 될거 같아서 메서드 하나로 통합
   // 게시글 입력 시 BoardDto 객체 반환
  public BoardDto getInput()
  {

    System.out.print("게시글 글ID, 제목, 내용을 입력하세요 (예: 1,제목,내용): ");
    String postInput = scanner.nextLine();
    String[] parts = postInput.split(",", 3);

    if (parts.length != 3) {
        System.out.println("Error! 입력 형식이 잘못되었습니다. '인덱스,제목,내용' 형식으로 입력해주세요.");
        return null;
    }

    try {
        int index = Integer.parseInt(parts[0].trim());
        String title = parts[1].trim();
        String content = parts[2].trim();
        return new BoardDto(index, title, content);
    } catch (NumberFormatException e) {
        System.out.println("Error! 인덱스는 숫자로 입력해야 합니다.");
        return null;
    }
  }


    // 사용자가 수정 또는 삭제할 게시글의 인덱스 입력 
    public int getIndex() 
    {
        while (true) {
            System.out.print("게시글 인덱스를 입력하세요: ");
            try {
                int index = scanner.nextInt();
                scanner.nextLine(); // 버퍼 비우기
                return index;
            } catch (InputMismatchException e) {
                System.out.println("[오류] 인덱스는 숫자로 입력해야 합니다.");
                scanner.nextLine(); // 잘못된 입력 버퍼 비우기
                return -1;
            }
        }
    }

    //사용자가 수정하고 싶은 부분 숫자로 입력
    public int getUpdateChoice() 
    {
        while (true) {
            System.out.print("수정할 항목을 선택하세요 (1: 제목, 2: 내용, 3: 제목과 내용 모두): ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Error! 숫자를 입력해야 합니다. 다시 시도해주세요.");
                scanner.nextLine(); 
            }
        }
    }

    // 사용자가 게시글의 새로운 제목 입력
    public String getTitleInput() 
    {
        System.out.print("새로운 제목을 입력하세요: ");
        return scanner.nextLine().trim();
    }

    //사용자가 게시글의 새로운 내용 추가
    public String getContentInput() 
    {
        System.out.print("새로운 내용을 입력하세요: ");
        return scanner.nextLine().trim();
    }

    
    // AI 이미지로 변환할 게시글의 인덱스 입력받음
    public int getAiIndex() {
        try {
            System.out.print("이미지로 변환할 게시글 인덱스를 입력하세요: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error! 숫자를 입력해야 합니다.");
            scanner.nextLine(); 
            return -1; // 잘못된 입력 처리
        }
    }
    
}
