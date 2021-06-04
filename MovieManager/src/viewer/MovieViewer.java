package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MovieController;
import model.MovieDTO;
import util.ScannerUtil;

public class MovieViewer {
    private MovieController controller;
    private Scanner scanner;

    public MovieViewer() {
        scanner = new Scanner(System.in);
        controller = new MovieController();
    }

    
    
    public void showMovieList() {
        ArrayList<MovieDTO> allList = controller.selectAll();
        System.out.println("===== 현재 상영목록 =====");
        for (int i = 0; i < allList.size(); i++) {
            System.out.printf(" %d. %s \n", allList.get(i).getIndex(), allList.get(i).getTitle());
            System.out.println("----------------------");
        }

    }

    // selectOne 해서 하나 보여줄 것
    public void printOneMovieByIndex(int index) { // userChoice

        MovieDTO oneMovie = new MovieDTO();

        if (controller.validateIndex(index)) {
            oneMovie = controller.selectOneByIndex(index);
            System.out.printf("\t%d. [ %s ]\n", oneMovie.getIndex(), oneMovie.getTitle());
            System.out.println("==================================");
            System.out.printf("\t상영등급 : %s\n", oneMovie.getRate());
            System.out.println("----------------------------------");
            System.out.printf("\t줄거리 : \t%s\n", oneMovie.getSummary());
            System.out.println("----------------------------------");
        } else {
            System.out.println("유효하지 않은 영화번호입니다.");
        }
    }

    public void newMovieInput() {
        MovieDTO temp = new MovieDTO();
        String message;
        message = new String("영화 제목을 입력하세요.");
        temp.setTitle(ScannerUtil.nextLine(scanner, message));

        while (controller.validateMovieDTO(temp)) {
            System.out.println("이미 상영중인 영화입니다(중복)");
            message = new String("영화 제목을 입력하세요.");
            temp.setTitle(ScannerUtil.nextLine(scanner, message));
        }

        message = new String("영화 줄거리를 입력하세요.");
        temp.setSummary(ScannerUtil.nextLine(scanner, message));

        System.out.println("해당 상영등급의 번호를 입력하세요.");
        message = new String("1.전체관람가  2.12세 관람가  3.15세관람가  4.19세관람가");
        int choice = ScannerUtil.nextInt(scanner, message, 1, 4);

        temp.setRate(String.valueOf(choice));

        controller.add(temp);
        System.out.println(">> 해당 영화 추가가 완료 되었습니다.");
    }

    public void movieUpdate() {
        MovieDTO temp = new MovieDTO();
        String message;
        message = new String("수정하실 영화의 번호를 입력하세요.");
        temp.setIndex(ScannerUtil.nextInt(scanner, message));

        while (!controller.validateIndex(temp.getIndex())) {
            System.out.println("유효하지 않는 번호를 입력하셨습니다.");
            message = new String("수정하실 영화의 번호를 입력하세요.");
            temp.setIndex(ScannerUtil.nextInt(scanner, message));
        }

        message = new String("영화 제목을 입력하세요.");
        temp.setTitle(ScannerUtil.nextLine(scanner, message));

        message = new String("영화 줄거리를 입력하세요.");
        temp.setSummary(ScannerUtil.nextLine(scanner, message));

        System.out.println("해당 상영등급의 번호를 입력하세요.");
        message = new String("1.전체관람가  2.12세 관람가  3.15세관람가  4.19세관람가");
        int choice = ScannerUtil.nextInt(scanner, message, 1, 4);

        temp.setRate(String.valueOf(choice));

        controller.update(temp);
        System.out.println(">> 해당 영화 수정이 완료되었습니다.");
    }

    // 수정 - controller에 delete메소드 추가
    public void movieDelete() {
        String message = new String("삭제하실 영화의 번호를 입력하세요.");
        int index = ScannerUtil.nextInt(scanner, message);

        while (!controller.validateIndex(index)) {
            System.out.println("유효하지 않은 번호입니다.");
            message = new String("삭제하실 영화의 번호를 입력하세요.");
            index = ScannerUtil.nextInt(scanner, message);
        }

        MovieDTO temp = controller.selectOneByIndex(index);
        controller.movieRemove(temp);
        System.out.println(">> 해당 영화 삭제가 완료되었습니다.");
    }

    public MovieDTO selectNameById(int id) {
        return controller.selectOneByIndex(id);
    }

    // 추가 - 유효한 영화 번호확인(컨트롤러 이용)
    public boolean validateMovieId(int id) {
        return controller.validateIndex(id);
    }

    // 추가
    public ArrayList<MovieDTO> getMoiveListAll() {
        return controller.selectAll();
    }
}