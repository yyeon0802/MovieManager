package controller;

import java.util.ArrayList;

import model.TheaterDTO;

public class TheaterController {
    public ArrayList<TheaterDTO> list;
    private int id;

    public TheaterController() {
        list = new ArrayList<>();
        id = 1;

        TheaterDTO t = new TheaterDTO();
        t.setIndex(id++);
        t.setName("CGV");
        t.setLocation("강남");
        t.setCallNumber("02-111-1111");
        list.add(t);

        t = new TheaterDTO();
        t.setIndex(id++);
        t.setName("메가박스");
        t.setLocation("서초");
        t.setCallNumber("02-222-2222");
        list.add(t);
        
        t = new TheaterDTO();
        t.setIndex(id++);
        t.setName("메가박스22");
        t.setLocation("서초");
        t.setCallNumber("02-333-333");
        list.add(t);
        
        t = new TheaterDTO();
        t.setIndex(id++);
        t.setName("롯데시네마");
        t.setLocation("송파");
        t.setCallNumber("02-000-0000");
        list.add(t);
    }

 // (관리자 전용) 기존 극장 정보 수정하기
    public void update(TheaterDTO update) {
        for (TheaterDTO t : list) {
            if (t.getIndex() == update.getIndex()) {
                t.setName(update.getName());
                t.setLocation(update.getLocation());
                t.setCallNumber(update.getCallNumber());
            }
        }
    }

    // (관리자 전용) 기존 극장 정보 삭제하기
    public void delete(TheaterDTO t) {
        list.remove(t);
    }

    // 중복명 체크
    public boolean validateTheatername(String name) {
        for (TheaterDTO t : list) {
            if (t.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // 어레이리스트 넘겨주는 메소드
    public ArrayList<TheaterDTO> allList() {
        ArrayList<TheaterDTO> temp = new ArrayList<>();
        temp = list;
        return temp;
    }

    // 선택한 id 값과 일치하는 객체 반환
    public TheaterDTO selectOne(int id) {
        for (TheaterDTO t : list) {
            if (t.getIndex() == id) {
                return t;
            }
        }
        return null;
    }
    
    // (관리자 전용) 새로운 극장 등록하기
    public void add(TheaterDTO t) {
        t.setIndex(id++);
        list.add(t);
    }

    // 추가 - 유효한 극장 번호인지 확인
    public boolean validateIndex(int id) {
        for(TheaterDTO t:list) {
            if(t.getIndex() == id) {
                return true;
            }
        }
        return false;
    }

    
    
    

}
