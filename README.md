text_to_image_project

## 구현 사항
### 1. **프로젝트 구현 의도**
- **공학이란**
  - 기능개선 및 성능 향상을 목적으로 창의적 아이디어를 제품, 시스템으로 `구현`하는 것
  
- **공학 설계란**:
  - `새로운` 장치나 시스템을 만들어 내거나 기존의 것을 `개선`하는 과정.

- **text to image ai의 문제점**
  - 구현 원하는 내용을 글로 제공 -> ai가 그에 맞는 이미지를 생성하는 기술
  - 일반적인 글로 내용을 제공할 경우 예상한 이미지 구현 확률 낮아 (한글시 더 낮음)
  - 생성을 위해 사이트에 방문, 회원가입 후 사용가능 -> 사용자 편의성 낮음, 비용 발생 가능성
  - 사용 이후 타 기능과 연계성 낮음 -> 이미지 생성 이후 추가 기능이 있는가?
  
- **text to image ai 개선 사항**
  - Taxonomy of Prompt Modifiers for Text-To-Image
    Generation에 따라 정확한 프롬프트를 제공하여 이미지 생성 확률을 높임
  - 사이트 방문 없이 콘솔 내에서 ai 사용 가능하여 접근성 높임
  - 이미지 생성과 연계된 추가 기능 구현으로 기술 사용 유인 높임

### 2. **구현 목표**
- **목표 : 꿈 기록 게시판 구현, 게시글을 정확한 프롬프트로 변환 -> 꿈 내용 이미지로 변환** 
    - `java` 콘솔 내에서 `crud` 게시판 기능 구현
    -  콘솔 내에서 구현하는 문제 -> process 재시작 후에도 기존 기록 유지하기 위한 db 기능 구현
    -  사용자의 글을 기반으로 이미지 생성을 위한 적절한 프롬프트를 반환하는 기능
    -  변환된 프롬프트를 통해 이미지 생성기능 구현

### 3. **기술 문서**
  - `crud` 게시판 기능
  - `vector` 기반의 `db` 구현, 실제 파일에 io, db 내용의 영속성 유지
  - `gpt api` 통해 게시글을 적절한 프롬프트로 변환, 변환 전 api에 프롬프트 예시 제공
  - `huggingface api`를 사용하여 `사진 생성 및 다운로드` 기능 구현

- **view**
  - outputView
    - PrintBoard : 게시판 출력 (const ref Vector<obj>)
    - PrintIndex : 새 글쓰기, 수정하기, 삭제하기, ai 변환하기, 종료하기 index 출력()
    - PrintTitleRequest : 제목 입력 요청()
    - PrintContentRequest : 내용 입력 요청()
    - PrintUpdateIndexRequest : 수정할 글 index 요청()
    - PrintDeleteIndexRequest : 삭제할 글 index 요청()
    - PrintAiIndexRequest : ai 변환할 글 index 요청()
    - PrintUpdateTitleRequest : 수정할 제목 입력 요청()
    - PrintUpdateContentRequest : 수정할 내용 입력 요청()
    
  - inputView : 콘솔 입력 class
    - getIndex : 게시글 index 입력 받기()
    - getTitle : 제목 입력 받기()
    - getContent : 내용 입력 받기()
    
- **service**
  - BoardService : 게시판 기능 관리 class
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
    
  - PromptToImageManager : ai 이미지 생성 class (0)
    - requestHuggingface : 프롬프트를 통해 이미지 생성 요청 (HuggingfaceRequestDto 받아 이미지 생성) (0)
    - downloadImage : 이미지 다운로드 요청 (url 받아 이미지 다운로드) (0)

  
- **dto**
  - BoardDTO : 게시글 형식(제목, 내용)에 대한 전달 객체 class
  - GptRequestDto : 사용자 글 내용 -> api 요청 형태로 전달 객체
  - HuggingfaceRequestDto : api 요청 형태로 전달 객체 (0)

- **repository (DB)**
  - BoardDb : 게시판 db class
    - vector<boardDTO> database : vector 통한 db 구현
    - creteBoard : 게시글 생성 (boardDto 받아 add)
    - readBoard : 게시글 조회 (index 해당하는 boardDto 반환)
    - updateBoard : 게시글 수정 (boardDto 받아 해당 index 값 수정)
    - deleteBoard : 게시글 삭제 (index 해당하는 boardDto 삭제)
    - fullBoard : 게시글 전체 조회 (vector 반환)

- **Controller**
  - ProjectController : 전반적인 게시판 기능 동작 Class
