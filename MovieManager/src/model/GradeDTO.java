package model;

// 평점
public class GradeDTO {
    private int index;

    private int userIndex;

    private int movieIndex;

    private int starPoint;

    private String comment;

    private String userNickname;

    private int userGroup;

    // getters/setters
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public void setUserIndex(int userIndex) {
        this.userIndex = userIndex;
    }

    public int getMovieIndex() {
        return movieIndex;
    }

    public void setMovieIndex(int movieIndex) {
        this.movieIndex = movieIndex;
    }

    public int getStarPoint() {
        return starPoint;
    }

    public void setStarPoint(int starPoint) {
        this.starPoint = starPoint;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(int userGroup) {
        this.userGroup = userGroup;
    }

    // equals
    public boolean equals(Object o) {
        if (o instanceof GradeDTO) {
            GradeDTO g = (GradeDTO) o;
            if (index == g.index) {
                return true;
            }
        }
        return false;
    }
}
