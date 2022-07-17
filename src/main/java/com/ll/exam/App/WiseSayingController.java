package com.ll.exam.App;


import java.util.Scanner;

public class WiseSayingController {

    private Scanner sc;
    private WiseSayingRepository wiseSayingRepository;

    WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingRepository = new WiseSayingRepository();
    }

        public void modify (Rq rq){
            int paramId = rq.getIntParam("id", 0);

            if (paramId == 0) {
                System.out.println("id를 입력해주세요");
                return;
            }
            WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

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
            for (int i = wiseSayingRepository.wiseSayings.size() - 1; i >= 0; i--) {
                WiseSaying wiseSaying_ = wiseSayingRepository.wiseSayings.get(i);
                System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
            }
        }

        public void write (Rq rq){
            System.out.printf("명언 : ");
            String content = sc.nextLine().trim();
            System.out.printf("작가 : ");
            String author = sc.nextLine().trim();
            int id = wiseSayingRepository.wiseSayingId++;

            WiseSaying wiseSaying = new WiseSaying(id, content, author);
            wiseSayingRepository.wiseSayings.add(wiseSaying);

            System.out.printf("%d번 명언 등록\n", id);
        }

        public void remove (Rq rq){
            int paramId = rq.getIntParam("id", 0);

            if (paramId == 0) {
                System.out.println("원하시는 id가 존재하지 않습니다.");
                return;
            }
            WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

            if (foundWiseSaying == null) {
                System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
                return;
            }

            wiseSayingRepository.wiseSayings.remove(foundWiseSaying);
            System.out.printf("%d번 명언이 삭제 되었습니다.\n", paramId);

        }
    }


