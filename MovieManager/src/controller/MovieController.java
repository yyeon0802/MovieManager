package controller;

import java.util.ArrayList;

import model.MovieDTO;


public class MovieController {
    ArrayList<MovieDTO> list;
    int index;

    public MovieController() {
        
        list = new ArrayList<>();
        index = 1;
        
        MovieDTO m1 = new MovieDTO();
        m1.setIndex(index++);
        m1.setRate("1");
        m1.setTitle("뽀로로");
        m1.setSummary("뽀로로여행기");
        list.add(m1);
        
        MovieDTO m2 = new MovieDTO();
        m2.setIndex(index++);
        m2.setRate("2");
        m2.setTitle("포켓몬스터");
        m2.setSummary("피카츄여행기");
        list.add(m2);
        
        MovieDTO m3 = new MovieDTO();
        m3.setIndex(index++);
        m3.setRate("3");
        m3.setTitle("해리포터");
        m3.setSummary("해리포터여행기");
        list.add(m3);
        
        MovieDTO m4 = new MovieDTO();
        m4.setIndex(index++);
        m4.setRate("4");
        m4.setTitle("장화홍련");
        m4.setSummary("무서운이야기");
        list.add(m4);

    }

    
    public ArrayList<MovieDTO> selectAll () {

        return list;
    }

    
    public boolean validateIndex(int index) {
        for (MovieDTO m : list) {
            if (m.getIndex() == index) {
                return true;
            }
        }
        return false;
    }
        
    public boolean validateMovieDTO(MovieDTO temp) {
        for (MovieDTO m : list) {
            if (m.getTitle().equals(temp.getTitle())) {
                return true;
            }
        }
        return false;
    }

    
    public void add(MovieDTO m) {
        
        m.setIndex(index++);
        
        list.add(m);
    }

    public void update(MovieDTO updated) {
        for (MovieDTO m : list) {
            if (m.getIndex() == updated.getIndex()) {
                m.setTitle(updated.getTitle());
                m.setSummary(updated.getSummary());
                m.setRate(updated.getRate());
            }
        }
    }
    
    // 추가 - 삭제 메소드
    public void movieRemove(MovieDTO m) {
        list.remove(m);
    }
    
    
    public MovieDTO selectOneByIndex(int index) {
        for (MovieDTO m : list) {
            if (m.getIndex() == index) {
                return m;
            }
        }
        
        return null;
    }
    
        
    
}