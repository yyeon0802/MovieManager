package model;


public class MovieDTO {
    
    private int index;
   
    private String title;
    
    private String summary;

    private String rate;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        if (rate.equals("1")) {
            this.rate = new String("전체 관람가");
        } else if (rate.equals("2")) {
            this.rate = new String("12세 이상 관람가");
        } else if (rate.equals("3")) {
            this.rate = new String("15세 이상 관람가");
        } else if (rate.equals("4")) {
            this.rate = new String("19세 이상 관람가");
        }
    }
    

    // equals
    public boolean equals(Object o) {
        if (o instanceof MovieDTO) {
            MovieDTO m = (MovieDTO) o;
            if (index == m.index) {
                return true;
            }
        }
        return false;
    }
}