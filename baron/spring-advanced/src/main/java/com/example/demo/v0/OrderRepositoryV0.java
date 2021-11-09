package com.example.demo.v0;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId) {
        // ���옣 濡쒖쭅
        if (itemId.equals("ex")) {
            throw new IllegalStateException("error");
        }
        
        // �긽�뭹 ���옣�뿉 1珥덇� 嫄몃┛�떎怨� 媛��젙
        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
