package io.silver.sys;

import io.silver.controller.BoardController;
import io.silver.controller.PostController;
import io.silver.data.Post;
import java.util.Scanner;

public class Application {

    private String domain;
    private boolean programStatus = true;
    Scanner sc = new Scanner(System.in);

    public Application(String domain) {
        this.domain = domain;
    }

    public void run() {

        PostController postController = new PostController();
        BoardController boardController = new BoardController();

        while( programStatus ) {

            System.out.print("https://" + domain);
            String command = sc.nextLine().trim();

            if(command.equals(".exit")){
                System.out.println("Exiting the Application... ");
                break;
            }

            Request request = new Request(command);

            if(!request.isValid()){
                System.out.println("잘못된 형식의 입력입니다 !");
                continue;
            }

            switch( request.getControllerCode() ) {
                case "posts" :
                    postController.requestHandle(request);
                    break;

                case "boards" :
                    boardController.requestHandle(request);
                    break;

                default :
                    System.out.println("존재하지 않는 명령어");
            }



        }


    }
}
