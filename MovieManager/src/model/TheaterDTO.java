package model;

// 극장
public class TheaterDTO {
    // 극장 번호
    private int index;

    // 극장 이름
    private String name;

    // 극장 위치
    private String location;

    // 극장 전화번호(int도 고려)
    private String callNumber;

    // getters/setters
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    // equals
    public boolean equals(Object o) {
        if (o instanceof TheaterDTO) {
            TheaterDTO t = (TheaterDTO) o;
            if (index == t.index) {
                return true;
            }
        }
        return false;
    }
}
