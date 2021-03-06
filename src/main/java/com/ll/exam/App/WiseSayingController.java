package com.ll.exam.App;


import java.util.List;
import java.util.Scanner;

public class WiseSayingController {

    private Scanner sc;
    private WiseSayingService wiseSayingService;

    WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingService = new WiseSayingService();
    }

        public void modify (Rq rq){
            int id = rq.getIntParam("id", 0);

            if (id== 0) {
                System.out.println("id를 입력해주세요");
                return;
            }
            WiseSaying wiseSaying = wiseSayingService.findById(id);

            if(wiseSaying == null){
                System.out.printf("%d번 명언은 존재하지 않아요.\n");
                return;
            }


            System.out.printf("명언(기존) : %s\n", wiseSaying.content);
            System.out.printf("명언 : ");
            String content = sc.nextLine();
            System.out.printf("작가(기존) : %s\n", wiseSaying.author);
            System.out.printf("작가 : ");
            String author = sc.nextLine();

            wiseSayingService.modify(id, content, author);
            System.out.printf("%d번 명언이 수정 되었습니다.\n", id);

        }

        public void list (Rq rq){
            System.out.println("번호 / 작가 / 명언");
            System.out.println("================");

            List<WiseSaying> wiseSayings = wiseSayingService.findAll();

            for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                WiseSaying wiseSaying = wiseSayings.get(i);
                System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.content, wiseSaying.author);
            }
        }

        public void write (Rq rq){
            System.out.printf("명언 : ");
            String content = sc.nextLine();
            System.out.printf("작가 : ");
            String author = sc.nextLine();
            WiseSaying wiseSaying = wiseSayingService.write(content,author);
            System.out.printf("%d번 명언 등록\n", wiseSaying.id);
        }

        public void remove (Rq rq){
            int id = rq.getIntParam("id", 0);

            if (id == 0) {
                System.out.println("원하시는 id가 존재하지 않습니다.");
                return;
            }
            WiseSaying wiseSaying = wiseSayingService.findById(id);

            if (wiseSaying == null) {
                System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
                return;
            }

            wiseSayingService.remove(id);
            System.out.printf("%d번 명언이 삭제 되었습니다.\n", id);

        }
        public void build(Rq rq){
            wiseSayingService.dumpToJson();
        }
    }


