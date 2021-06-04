package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.InfoController;
import model.InfoDTO;
import model.MovieDTO;
import util.ScannerUtil;

public class InfoViewer {

    private Scanner scanner;
    private InfoController controller;
    private MovieViewer movieViewer;
    private TheaterViewer theaterViewer;

    public InfoViewer(MovieViewer movieViewer, TheaterViewer theaterViewer) {
        scanner = new Scanner(System.in);
        controller = new InfoController();

        // 의존성 주입 - 해당 클래스에서 validate메소드 필요
        this.movieViewer = movieViewer;
        this.theaterViewer = theaterViewer;
    }

    // 영화id를 가져와서
    // 관리자로부터 새로운 상영 정보 등록
    public void registerMovieInfo() {
        InfoDTO m = new InfoDTO();

        String message;

        message = new String("등록하실 영화 번호를 입력해주세요");
        int id = ScannerUtil.nextInt(scanner, message);

        // 영화 번호 유효성 확인 메소드 이용 - 파라미터로 보내서 확인
        while (!movieViewer.validateMovieId(id)) {
            System.out.println();
            System.out.println("존재하지 않는 영화 번호입니다.");
            id = ScannerUtil.nextInt(scanner, message);

        }
        m.setMovieIndex(id);

        message = new String("등록하실 극장 번호를 입력해주세요");
        id = ScannerUtil.nextInt(scanner, message);

        // 극장 번호 유효성 확인 메소드 이용 - 파라미터로 보내서 확인
        while (!theaterViewer.validateTheaterId(id)) {
            System.out.println("존재하지 않는 극장 번호입니다");
            id = ScannerUtil.nextInt(scanner, message);
        }
        m.setTheaterIndex(id);

        message = new String("등록하실 상영 시간을 입력해주세요(ex. 00시 00분)");
        m.setRunningTime(ScannerUtil.nextLine(scanner, message));

        controller.add(m);
    }

    // 기존 상영 정보 수정
    public void updateMovieInfo() {
        String message;

        message = new String("수정을 원하는 상영 번호를 입력해주세요");
        int id = ScannerUtil.nextInt(scanner, message);

        while (!controller.validateMovieInfo(id)) {
            System.out.println();
            message = new String("해당 상영 번호는 유효하지 않습니다. 다시 입력하시겠습니까? Y/N");
            String yesNo = ScannerUtil.nextLine(scanner, message);

            if (yesNo.equalsIgnoreCase("n")) {
                return;
            }

            message = new String("수정을 원하는 상영 번호를 다시 입력해주세요");
            id = ScannerUtil.nextInt(scanner, message);
        }

        InfoDTO m = controller.selectOne(id);

        message = new String("수정하실 영화 번호를 입력해주세요");
        id = ScannerUtil.nextInt(scanner, message);

        // 영화 번호 유효성 확인 메소드 이용 - 파라미터로 보내서 확인
        while (!movieViewer.validateMovieId(id)) {
            System.out.println();
            System.out.println("존재하지 않는 영화 번호입니다.");
            id = ScannerUtil.nextInt(scanner, message);
        }
        m.setMovieIndex(id);

        message = new String("수정하실 극장 번호를 입력해주세요");
        id = ScannerUtil.nextInt(scanner, message);

        // 극장 번호 유효성 확인 메소드 이용 - 파라미터로 보내서 확인
        while (!theaterViewer.validateTheaterId(id)) {
            System.out.println("존재하지 않는 극장 번호입니다");
            id = ScannerUtil.nextInt(scanner, message);
        }
        m.setTheaterIndex(id);

        message = new String("수정하실 상영 시간을 입력해주세요(ex. 00시 00분)");
        m.setRunningTime(ScannerUtil.nextLine(scanner, message));

        controller.updateMovieInfo(m);
    }

    // 기존 상영 정보 삭제 - 선택한 상영 번호의 id를 가진 객체 선택해 삭제
    public void deleteMovieInfo() {
        String message = new String("삭제하실 상영 번호를 입력해주세요");
        int id = ScannerUtil.nextInt(scanner, message);

        InfoDTO m = controller.selectOne(id); // 삭제하고자 하는 객체 삽입

        System.out.println();
        message = new String("정말 삭제하시겠습니까? Y/N");
        String yesNo = ScannerUtil.nextLine(scanner, message);

        if (yesNo.equalsIgnoreCase("y")) {
            controller.delete(m);
        }
        System.out.println("삭제되었습니다.");
    }

    // 상영정보 전체보기
    public void printLIst() {
        // 영화번호를 넣으면 그에 해당하는 영화이름 가져오기
        // 극장번호를 넣으면 그에 해당하는 극장위치 가져오기
        // 컨트롤러는 감춰져 있기 때문에 사용하려면
        // 뷰어에서 컨트롤러의 메소드를 사용할 수 있는 메소드를 따로 만들어줘야 함
        ArrayList<InfoDTO> list = controller.selectAll();
        System.out.println("상영번호 \t영화이름 \t극장위치 \t상영시간");
        if (list.isEmpty()) {
            System.out.println("------------------------------------------");
            System.out.println("\t아직 등록된 상영번호가 없습니다.");
        } else {
            for (InfoDTO m : list) {
                ArrayList<MovieDTO> movieList = movieViewer.getMoiveListAll();
                for (int i = 0; i < movieList.size(); i++) {
                    if (m.getMovieIndex() == movieList.get(i).getIndex()) {

                        int movieId = m.getMovieIndex(); // 인덱스에 맞는 영화 이름 인덱스를 movie의 인자값에 보내기
                        int theaterId = m.getTheaterIndex();

                        System.out.printf("  %d.  %5s  %5s  %5s\n", m.getIndex(),
                                movieViewer.selectNameById(movieId).getTitle(),
                                theaterViewer.selectOneByIndex(theaterId).getLocation(), m.getRunningTime());
                    }
                }
            }
        }

    }

    // 추가 - 극장번호가 같은 영화들을 리턴하기 위한 메소드
    private void selectOneMoive() {
        // 극장 번호와 인덱스 안내해주는 출력문 필요?
        String message;
        message = new String("가고자하는 극장 번호를 선택해주세요. 0을 누르면 뒤로갑니다.");
        int theaterId = ScannerUtil.nextInt(scanner, message);

        // while문?
        //        if(theaterId == 0) {
        //            System.out.println("상위메뉴로 돌아갑니다.");
        //            break;
        //        }

        // 극장 유효번호 확인
        // 없으면 유효하지 않다고 출력
        while (!theaterViewer.validateTheaterId(theaterId)) {
            System.out.println();
            System.out.println("해당 극장 번호는 유효하지 않습니다.");
            System.out.println("다시 확인해주세요.");
            theaterId = ScannerUtil.nextInt(scanner, message);
        }

        // 존재하는 theaterId를 가지고, 관련 정보를 담은 리스트 가져오기
        ArrayList<InfoDTO> tmp = controller.selectMovieByTheaterId(theaterId);

        // 정보 출력
        System.out.println("==========================================");
        System.out.println("극장번호\t극장위치\t영화이름\t상영시간");
        for (InfoDTO i : tmp) {
            System.out.printf("%d. %5s %s %s\n", theaterViewer.selectOneByIndex(theaterId).getIndex(),
                    theaterViewer.selectOneByIndex(theaterId).getLocation(),
                    movieViewer.selectNameById(theaterId).getTitle(), i.getRunningTime());
        }
    }

    // 추가 - 외부에서 사용하기 위한 메소드
    // 같은 TheaterId를 가지는 상세정보 객체들을 배열로 모아 리턴해주는 메소드
    public ArrayList<InfoDTO> printOne(int theaterId) {
        return controller.selectMovieByTheaterId(theaterId);
    }
}
