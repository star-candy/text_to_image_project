package dto;

import java.io.Serializable;

public class BoardDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int index;
    private String title;
    private String content;

    public BoardDto(int index, String title, String content) {
        this.index = index;
        this.title = title;
        this.content = content;
    }

    // Getter와 Setter 메서드 추가
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "글ID=" + index +
                ", 제목='" + title + '\'' +
                ", 내용='" + content + '\'' +
                '}';
    }
}