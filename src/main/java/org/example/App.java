package org.example;
import java.io.IOException;
import java.util.Scanner;
import org.example.model.PromptToImageManager;

public class App 
{
    public static void main(String[] args) {
        PromptToImageManager promptToImageManager = new PromptToImageManager();
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("Enter the path of the image file or type 'exit' to quit: ");
            String path = sc.nextLine();
            if (path.equals("exit")) {
                break;
            }
            promptToImageManager.downloadImage(path);
        }


    }
}
