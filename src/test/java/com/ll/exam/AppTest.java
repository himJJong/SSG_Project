package com.ll.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.ll.exam.App.Rq;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AppTest {
    @Test
    public void 테스트_실험() {
        int rs = 10 + 20;
        assertEquals(30, rs);
    }
    @Test
    public void 문자열을_스캐너의_입력으로_설정(){
        String input = """
                등록
                명언1
                작가1
                """.stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);

        String cmd = sc.nextLine().trim();
        String content = sc.nextLine().trim();
        String author = sc.nextLine().trim();

        assertEquals("등록",cmd);
        assertEquals("명언1",content);
        assertEquals("작가1",author);
    }
    @Test
    public void 표준출력을_리다이렉션하여_결과를_문자열_받기()throws IOException {
        //표준출력 리다이렉션
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        System.out.println("안녕");
        String rs = output.toString().trim();

        //표준출력 원상복구
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        output.close();

        assertEquals("안녕",rs);
    }

    @Test
    public void Rq__getIntParam() {
        Rq rq = new Rq("삭제?id=1");

        int id = rq.getIntParam("id", 0);

        assertEquals(1, id);
    }

    @Test
    public void Rq__getIntParam__2() {
        Rq rq = new Rq("검색?id=10&no=1");

        int id = rq.getIntParam("id", 0);
        int no = rq.getIntParam("no", 0);

        assertEquals(10, id);
        assertEquals(1, no);
    }

    @Test
    public void Rq_getPath(){
        Rq rq = new Rq("삭제?id=1");

        String path = rq.getPath();

        assertEquals("삭제",path);
    }

}