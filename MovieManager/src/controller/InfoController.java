package controller;

import java.util.ArrayList;

import model.InfoDTO;

public class InfoController {
    private ArrayList<InfoDTO> list;
    private int index;

    // 만약, 같은 극장 / 같은 상영시간으로 묶으려면
    // 생성자
    public InfoController() {
        list = new ArrayList<>();
        index = 1; // 상영정보 번호

        // 데이터
        InfoDTO m = new InfoDTO();
        m.setIndex(index++);
        m.setMovieIndex(1);
        m.setTheaterIndex(1);
        m.setRunningTime("13시 10분"); // String?? 130분
        list.add(m);

        m = new InfoDTO();
        m.setIndex(index++);
        m.setMovieIndex(2);
        m.setTheaterIndex(1);
        m.setRunningTime("22시 50분");
        list.add(m);

        m = new InfoDTO();
        m.setIndex(index++);
        m.setMovieIndex(3);
        m.setTheaterIndex(3);
        m.setRunningTime("07시 40분");
        list.add(m);

        m = new InfoDTO();
        m.setIndex(index++);
        m.setMovieIndex(4);
        m.setTheaterIndex(4);
        m.setRunningTime("11시 10분");
        list.add(m);
    }

    // 상영정보 추가
    // 사용자가 입력한 값을 객체 형태로 받아 추가 - 새롭게 값 넣음
    public void add(InfoDTO m) {
        m.setIndex(index++);
        list.add(m);
    }

    // 상영정보 삭제
    public void delete(InfoDTO m) {
        list.remove(m);
    }

    // 파라미터로 들어온 id를 찾아 개별 보기할 상영정보 리턴
    public InfoDTO selectOne(int index) {
        for (InfoDTO m : list) {
            if (m.getIndex() == index) {
                return m;
            }
        }
        return null;
    }

    public InfoDTO selectOneByMovieIndex(int index) {
        for (InfoDTO m : list) {
            if (m.getMovieIndex() == index) {
                return m;
            }
        }
        return null;
    }

    // 기존에 있는 상영정보인지 확인
    public boolean validateMovieInfo(int index) {
        for (InfoDTO m : list) {
            if (m.getIndex() == index) {
                return true;
            }
        }
        return false;
    }

    // 상영 정보 전부 반환
    public ArrayList<InfoDTO> selectAll() {
        return list;
    }

    // 파라미터로 들어온 상영정보 번호를 받아 기존정보에 업데이트
    public void updateMovieInfo(InfoDTO updated) {
        for (InfoDTO m : list) {
            if (m.getIndex() == updated.getIndex()) {
                m.setMovieIndex(updated.getMovieIndex());
                m.setTheaterIndex(updated.getTheaterIndex());
                m.setRunningTime(updated.getRunningTime());
            }
        }
    }

    // 극장 id값을 받으면, 같은 아이디를 가진 영화들끼리 묶어주는 메소드
    public ArrayList<InfoDTO> selectMovieByTheaterId(int theaterId) {
        ArrayList<InfoDTO> tmp = new ArrayList<>(); // 같은 극장 id를 가지는 애들을
        for (InfoDTO i : list) {
            if (i.getTheaterIndex() == theaterId) {
                tmp.add(i);
            }
        }

        return tmp;
    }

    // 2. 극장의 id값을 받으면 해당 id값과 일치하는 infoDTO객체들을
    // ArrayList에 담아서 리턴하는 selectById() 메소드

}
