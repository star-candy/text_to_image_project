package model;

import dto.BoardDto;
import java.io.*;
import java.util.Vector;

// 파일 입출력을 관리하는 클래스
public class FileIoManager {
    private final String filePath;

    public FileIoManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveDbToFile(Vector<BoardDto> boardDb) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(boardDb);
            System.out.println("=== 데이터 저장 성공. ===");
        } catch (IOException e) {
            System.err.println("Error saving database: " + e.getMessage());
        }
    }

    public Vector<BoardDto> loadDbFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            @SuppressWarnings("unchecked")
            Vector<BoardDto> loadedDb = (Vector<BoardDto>) ois.readObject();
            System.out.println("=== 데이터 로드 성공. ===");
            return loadedDb;
        } catch (FileNotFoundException e) {
            System.out.println("No previous database found. Starting with an empty database.");
            return new Vector<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading database: " + e.getMessage());
            return new Vector<>();
        }
    }
}
