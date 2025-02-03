## 팀 프로젝트 기술 문서
- gradle 버전 8.5 사용
- 자바 17 버전 사용

### 2. **커밋 규정**
- 커밋 전 커밋 메시지 규정 확인하기
- `https://velog.io/@shin6403/Git-git-%EC%BB%A4%EB%B0%8B-%EC%BB%A8%EB%B2%A4%EC%85%98-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0 블로그 참조
- 프로젝트 업데이트 사항 확인 후 커밋하기


### 3. **기술 문서**
- `crud` 게시판 기능
- `vector` 기반의 `db` 구현, 실제 파일에 io, db 내용의 영속성 유지
- `gpt api` 통해 게시글을 적절한 프롬프트로 변환, 변환 전 api에 프롬프트 예시 제공
- `huggingface api`를 사용하여 `사진 생성 및 다운로드` 기능 구현


- **Controller**
    - ProjectController : 전반적인 게시판 기능 동작 Class
  
- **view**
    - outputView
        - PrintBoard : 게시판 출력
        - PrintIndex : 새 글쓰기, 수정하기, 삭제하기, ai 변환하기, 종료하기 index 출력
        - PrintUpdateIndexRequest : 수정할 글 index 요청
        - PrintDeleteIndexRequest : 삭제할 글 index 요청
        - PrintAiIndexRequest : ai 변환할 글 index 요청
        - PrintUpdateTitleRequest : 수정할 제목 입력 요청
        - PrintUpdateContentRequest : 수정할 내용 입력 요청

    - inputView : 콘솔 입력 class
        - getIndex : 게시글 index 입력 받기
        - getTitle : 제목 입력 요청 및 반환
        - getContent : 내용 입력 요청 및 반환

- **service**
    - BoardService : 게시판 전체 기능 관리 class
        - 게시판 자료구조 저장 위치?
    - TextToImageService : ai 기능 관리 class

- **model**
    - CrudManager : 게시판 crud 기능 관리 class 
        - requestCreate : 게시글 생성 요청 (BoardDto 받아 db에 추가) 
        - requestRead : 게시글 조회 요청 (index 받아 BoardDto 반환) 
        - requestUpdate : 게시글 수정 요청 (index, BoardDto 받아 수정) 
        - requestDelete : 게시글 삭제 요청 (index 받아 삭제) 
        - requestFull : 게시글 전체 조회 요청 (vector 반환)

    - FileIoManager : db 내용과 파일 간 상호작용 class
        - saveDbToFile : db 내용을 파일에 저장 (process 종료시 사용할 것)
        - loadDbFromFile : 파일 내용을 db에 로드 (process 시작시 사용할 것)
      
    - TextToPromptManager : ai 프롬프트 변환 class
        - requestGpt : 게시글을 ai 프롬프트로 변환 요청 (GptRequestDto 받아 변환)

    - PromptToImageManager : ai 이미지 생성 class 
        - requestHuggingface : 프롬프트를 통해 이미지 생성 요청 (HuggingfaceRequestDto 받아 이미지 생성) 
        - downloadImage : 이미지 다운로드 요청 (url 받아 이미지 다운로드) 

- **dto**
    - BoardDTO : 게시글 형식(제목, 내용)에 대한 형식 객체 class 
    - GptRequestDto : 사용자 글 내용 -> api 요청 형태로 전달 객체
    - HuggingfaceRequestDto : api 요청 형태로 전달 객체 

