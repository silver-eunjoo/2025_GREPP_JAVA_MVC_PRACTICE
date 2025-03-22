package io.silver.sys;

import java.util.Scanner;

public class Application {

    private String domain;
    private boolean programStatus = true;
    Scanner sc = new Scanner(System.in);

    public Application(String domain) {
        this.domain = domain;
    }

    public void run() {

        System.out.print("https://");
        String command = sc.nextLine().trim();

        while( programStatus ) {

            Request request = new Request(command);

            if(!request.isValid()){

            }



        }


    }
}
