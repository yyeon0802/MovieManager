package model;

// 상영정보
public class InfoDTO {
    // 상영정보 번호
    private int index;

    // 영화 번호
    private int movieIndex;

    // 극장 번호
    private int theaterIndex;

    // 상영 시간
    private String runningTime;

    public int getIndex() {
        return index;
    }

    // getters/setters
    public void setIndex(int index) {
        this.index = index;
    }

    public int getMovieIndex() {
        return movieIndex;
    }

    public void setMovieIndex(int movieIndex) {
        this.movieIndex = movieIndex;
    }

    public int getTheaterIndex() {
        return theaterIndex;
    }

    public void setTheaterIndex(int theaterIndex) {
        this.theaterIndex = theaterIndex;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    // equals
    public boolean equals(Object o) {
        if (o instanceof InfoDTO) {
            InfoDTO i = (InfoDTO) o;
            if (index == i.index) {
                return true;
            }
        }
        return false;
    }
}
