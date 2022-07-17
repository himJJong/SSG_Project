package com.ll.exam.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner sc;
    private List<WiseSaying> wiseSayings;
    private  int wiseSayingId;

    public App() {
        sc = new Scanner(System.in);
        wiseSayings = new ArrayList<>();
        wiseSayingId = 0;
    }

    public void run(){
        System.out.println("====== SSG명언 ======");

        outer:
        while(true){

            System.out.printf("명령:");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);

            switch(rq.getPath()){
                case "종료":
                    break outer;

                case "등록":
                    write(rq);
                    break;

                case "목록":
                    list(rq);
                    break;
                case "삭제":
                    remove(rq);
                    break;

                case "수정":
                    modify(rq);
                    break;
            }
        }
        sc.close();
    }

    private void modify(Rq rq) {
        int paramId = rq.getIntParam("id",0);

        if(paramId == 0){
            System.out.println("원하시는 id가 존재하지 않습니다.");
            return;
        }
        WiseSaying foundWiseSaying = findById(paramId);

        System.out.printf("명언(기존) : %s\n", foundWiseSaying.content);
        System.out.printf("명언 : ");
        foundWiseSaying.content = sc.nextLine();
        System.out.printf("작가(기존) : %s\n", foundWiseSaying.author);
        System.out.printf("작가 : ");
        foundWiseSaying.author = sc.nextLine();

        System.out.printf("%d번 명언이 수정 되었습니다.\n",paramId);

    }

    private void list(Rq rq){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("================");
        for(int i =wiseSayings.size()-1 ; i>=0 ; i--) {
            WiseSaying wiseSaying_ = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }

    private void write(Rq rq){
        System.out.printf("명언 : ");
        String content = sc.nextLine().trim();
        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();
        int id = wiseSayingId++;

        WiseSaying wiseSaying = new WiseSaying(id, content,author);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언 등록\n",id);
    }

    private void remove(Rq rq){
        int paramId = rq.getIntParam("id",0);

        if(paramId == 0){
            System.out.println("원하시는 id가 존재하지 않습니다.");
            return;
        }
        WiseSaying foundWiseSaying = findById(paramId);

        if(foundWiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",paramId);
            return;
        }

        wiseSayings.remove(foundWiseSaying);

        System.out.printf("%d번 명언이 삭제 되었습니다.\n",paramId);

    }

    private WiseSaying findById(int paramId) {
        for(WiseSaying wiseSaying:wiseSayings){
            if(wiseSaying.id==paramId){
                return wiseSaying;
            }
        }
        return null;
    }
}
