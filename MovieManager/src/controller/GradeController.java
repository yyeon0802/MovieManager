package controller;

import java.util.ArrayList;

import model.GradeDTO;
import model.UserDTO;

public class GradeController {
    ArrayList<GradeDTO> list;
    int index;

    public GradeController() {
        list = new ArrayList<>();
        index = 1;
    }

    public void add(GradeDTO g) {
        g.setIndex(index++);
        list.add(g);
    }

    public boolean validateByLogInDTO(int movieIndex, UserDTO mainLogIn) {
        for (GradeDTO g : list) {
            if (g.getMovieIndex() == movieIndex && g.getUserIndex() == mainLogIn.getIndex()) {
                return true;
            }
        }
        return false;
    }
    
    public GradeDTO selectGradeOneByLogInDTO(int movieIndex, UserDTO mainLogIn) {
        for (GradeDTO g : list) {
            if (g.getMovieIndex() == movieIndex && g.getUserIndex() == mainLogIn.getIndex()) {
                return g;
            }
        }
        return null;
    }
    
    
    public GradeDTO selectByMovieIndex(int movieIndex) {
        for (GradeDTO g : list) {
            if (g.getMovieIndex() == movieIndex) {
                return g;
            }
        }

        return null;
    }

    public ArrayList<GradeDTO> group1List(int movieIndex) {
        ArrayList<GradeDTO> temp = new ArrayList<>();
        for (GradeDTO g : list) {
            if (g.getMovieIndex() == movieIndex) {
                if (g.getUserGroup() == 1) {
                    temp.add(g);
                }
            }
        }
        return temp;
    }

    public ArrayList<GradeDTO> group2List(int movieIndex) {
        ArrayList<GradeDTO> temp = new ArrayList<>();
        for (GradeDTO g : list) {
            if (g.getMovieIndex() == movieIndex) {
                if (g.getUserGroup() == 2) {
                    temp.add(g);
                }
            }
        }
        return temp;
    }
   
    public double totalPoint(int movieIndex) {
        ArrayList<GradeDTO> temp = new ArrayList<>();
        int sum = 0;
        int count = 0;
        for (GradeDTO g : list) {
            if (g.getMovieIndex() == movieIndex) {
                sum += g.getStarPoint();
                count++;
            }
        }

        if (count > 0) {
            double average = sum / (double) count;
            return average;
        } 
        
        return 0.0;
    }
    
    public void deleteGradeByIndex(GradeDTO g) {
        list.remove(g);
    }

}
