package com.ll.exam.App;

import java.util.Scanner;

public class App {
    private static String mode = "prod";
    private Scanner sc;

    public App(Scanner sc)
    {
        this.sc = sc;
    }
    public static String getDataBaseDir(){
        return mode + "_data";
    }

    public static void setMode(String mode){
        App.mode = mode;
    }

    public void run() {
        System.out.println("====== SSG명언 ======");

        WiseSayingController WiseSayingController = new WiseSayingController(sc);

        outer:
        while (true) {

            System.out.printf("명령:");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "종료":
                    break outer;

                case "등록":
                    WiseSayingController.write(rq);
                    break;

                case "목록":
                    WiseSayingController.list(rq);
                    break;
                case "삭제":
                    WiseSayingController.remove(rq);
                    break;

                case "수정":
                    WiseSayingController.modify(rq);
                    break;
            }
        }
    }
}

