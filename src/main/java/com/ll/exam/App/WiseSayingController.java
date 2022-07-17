package com.ll.exam.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private List<WiseSaying> wiseSayings;
    private int wiseSayingId;
    private Scanner sc;

    WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayings = new ArrayList<>();
        wiseSayingId = 0;
    }

        public void modify (Rq rq){
            int paramId = rq.getIntParam("id", 0);

            if (paramId == 0) {
                System.out.println("id를 입력해주세요");
                return;
            }
            WiseSaying foundWiseSaying = findById(paramId);

            if(foundWiseSaying == null){
                System.out.printf("%d번 명언은 존재하지 않아요.\n");
                return;
            }


            System.out.printf("명언(기존) : %s\n", foundWiseSaying.content);
            System.out.printf("명언 : ");
            foundWiseSaying.content = sc.nextLine();
            System.out.printf("작가(기존) : %s\n", foundWiseSaying.author);
            System.out.printf("작가 : ");
            foundWiseSaying.author = sc.nextLine();

            System.out.printf("%d번 명언이 수정 되었습니다.\n", paramId);

        }

        public void list (Rq rq){
            System.out.println("번호 / 작가 / 명언");
            System.out.println("================");
            for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                WiseSaying wiseSaying_ = wiseSayings.get(i);
                System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
            }
        }

        public void write (Rq rq){
            System.out.printf("명언 : ");
            String content = sc.nextLine().trim();
            System.out.printf("작가 : ");
            String author = sc.nextLine().trim();
            int id = wiseSayingId++;

            WiseSaying wiseSaying = new WiseSaying(id, content, author);
            wiseSayings.add(wiseSaying);

            System.out.printf("%d번 명언 등록\n", id);
        }

        public void remove (Rq rq){
            int paramId = rq.getIntParam("id", 0);

            if (paramId == 0) {
                System.out.println("원하시는 id가 존재하지 않습니다.");
                return;
            }
            WiseSaying foundWiseSaying = findById(paramId);

            if (foundWiseSaying == null) {
                System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
                return;
            }

            wiseSayings.remove(foundWiseSaying);

            System.out.printf("%d번 명언이 삭제 되었습니다.\n", paramId);

        }

        public WiseSaying findById ( int paramId){
            for (WiseSaying wiseSaying : wiseSayings) {
                if (wiseSaying.id == paramId) {
                    return wiseSaying;
                }
            }
            return null;
        }
    }


