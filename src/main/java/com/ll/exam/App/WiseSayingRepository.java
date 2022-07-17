package com.ll.exam.App;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    public List<WiseSaying> wiseSayings;
    public int wiseSayingId;

    WiseSayingRepository(){
        wiseSayings = new ArrayList<>();
        wiseSayingId = 0;
    }
    public WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) {
                return wiseSaying;
            }
        }

        return null;
    }
}


