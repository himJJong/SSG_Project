package com.ll.exam.App;

import java.util.Scanner;

public class App {
    public void run(){
        System.out.println("====== SSG명언 ======");
        int wiseSayingId = 0;
        Scanner sc = new Scanner(System.in);
        outer:
        while(true){

            System.out.printf("명령:");
            String cmd = sc.nextLine().trim();

            switch(cmd){
                case "종료":
                    break outer;
                case "등록":
                    System.out.printf("명언 : ");
                    String content = sc.nextLine().trim();
                    System.out.printf("작가 : ");
                    String author = sc.nextLine().trim();
                    int id = wiseSayingId++;

                    WiseSaying wiseSaying = new WiseSaying(id, content,author);

                    System.out.printf("%d번 명언 등록\n",id);
                    break;


            }
        }
        sc.close();
    }
}
