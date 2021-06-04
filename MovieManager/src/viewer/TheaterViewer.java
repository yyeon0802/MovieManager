package viewer;

// 고칠 점

// 1. 극장 정보 개별 보기 들어갔을 때 상영 정보 출력

import java.util.ArrayList;
import java.util.Scanner;

import controller.TheaterController;
import model.InfoDTO;
import model.MovieDTO;
import model.TheaterDTO;
import util.ScannerUtil;

// 극장 목록
public class TheaterViewer {
    private Scanner scanner;
    private TheaterController controller;
    private UserViewer userViewer;
    private InfoViewer infoViewer;
    private MovieViewer movieViewer;

    public TheaterViewer(UserViewer userViewer) {
        controller = new TheaterController();
        scanner = new Scanner(System.in);

        // 의존성 주입(Dependency Injection)을 하는 코드
        this.userViewer = userViewer;
    }

    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;

    }

    // setter로 의존성 주입?
    public void setInfoViewer(InfoViewer infoViewer) {
        this.infoViewer = infoViewer;
    }

    // 극장 전체 목록 보여주기
    public void theaterList() {
        ArrayList<TheaterDTO> list = controller.allList();
        while (true) {

            String message;

            // 극장 목록 없을 시 문구 출력
            if (list.isEmpty()) {
                System.out.println("====================");
                System.out.println("  등록된 극장 정보 없음  ");
                System.out.println("====================");
                break;
            }

            System.out.println("====================");
            System.out.println("       극장 관리      ");
            System.out.println("====================");
            for (TheaterDTO t : list) {
                System.out.printf("%d. %s\n", t.getIndex(), t.getName());
            }
            System.out.println("====================");

            if (userViewer.selectGroup() == 3) {

                System.out.println("관리자용 메뉴");
                message = new String("1.극장 추가 2. 극장 정보 수정 3. 극장 정보 삭제 4. 극장 상세 보기 0. 돌아가기");
                int userChoice = ScannerUtil.nextInt(scanner, message, 0, 4);
                if (userChoice == 1) {
                    insert();
                } else if (userChoice == 2) {
                    showUpdate();
                } else if (userChoice == 3) {
                    delete();
                } else if (userChoice == 4) {
                    theaterOne();
                } else if (userChoice == 0) {
                    System.out.println("상위 메뉴로");
                    System.out.println();
                    break;
                }

            } else {
                System.out.println("일반 메뉴(관람객&평론가용)");
                message = new String("1. 극장 상세 보기 0. 뒤로가기");
                int userChoice = ScannerUtil.nextInt(scanner, message, 0, 1);
                if (userChoice == 1) {
                    theaterOne();
                } else if (userChoice == 0) {
                    System.out.println("상위 메뉴로");
                    System.out.println();
                    break;
                }
            }
        }
    }

    // 극장 개별 보기
    public void theaterOne() {
        while (true) {
            ArrayList<TheaterDTO> list = controller.allList();
            System.out.println("====================");
            System.out.println("     극장 상세 정보    ");
            System.out.println("====================");
            for (TheaterDTO t : list) {
                System.out.printf("%d. %s\n", t.getIndex(), t.getName());
            }
            System.out.println("====================");

            String message = new String("열람할 극장 번호 선택(0: 돌아가기)");
            int userChoice = ScannerUtil.nextInt(scanner, message);
            TheaterDTO t = controller.selectOne(userChoice);
            if (userChoice != 0) {

                while (!(userChoice == 0 || list.contains(t))) {
                    System.out.println("잘못 입력하셨습니다");
                    userChoice = ScannerUtil.nextInt(scanner, message);
                    t = controller.selectOne(userChoice);
                }
            } else if (userChoice == 0) {
                System.out.println("상위 메뉴로");
                System.out.println();
                break;
            }

            while (true) {
                if (userChoice != 0) {

                    System.out.println("====================");
                    System.out.printf("극장명: %s\n극장 위치: %s\n극장 전화번호: %s\n", t.getName(), t.getLocation(),
                            t.getCallNumber());
                    System.out.println("====================");

                    System.out.println("상영중인 영화 목록");

                    // 상영정보 받아오기
                    // 파라미터 들어가는 메소드 따로 만들어서 삽입해주기
                    // 상영정보 뷰어에 영화관 id에 따라
                    // 그 영화관 id가 포함된 dto를 어레이리스트에 넣어서
                    // 리턴하는 메소드를 만들고
                    // 리턴된 어레이리스트를 무비뷰어의
                    // selectOne()을 통해서 출력해준다.

                    // 상영정보를 받아온다.
                    ArrayList<InfoDTO> infoList = infoViewer.printOne(t.getIndex());

                    // 받아온 상영정보 리스트에서 movieId를 사용하여
                    // movieViewer를 통해 movieDTO 객체를 받아온다.
                    for (InfoDTO i : infoList) {
                        MovieDTO m = movieViewer.selectNameById(i.getMovieIndex());
                        System.out.printf("%d. %s %s", i.getIndex(), m.getTitle(), i.getRunningTime());
                    }

                    System.out.println("====================");
                    message = new String("0: 돌아가기");
                    userChoice = ScannerUtil.nextInt(scanner, message, 0, 0);
                    break;
                } else if (userChoice == 0) {
                    System.out.println("상위 메뉴로");
                    System.out.println();
                    break;
                }
            }
        }
    }

    // 극장 등록 메소드
    public void insert() {
        TheaterDTO t = new TheaterDTO();
        String message = new String("극장 이름 입력");
        String name = ScannerUtil.nextLine(scanner, message);

        while (controller.validateTheatername(name)) {
            System.out.println("중복된 극장명");
            name = ScannerUtil.nextLine(scanner, message);
        }

        t.setName(name);

        message = new String("극장 위치 입력");
        t.setLocation(ScannerUtil.nextLine(scanner, message));

        message = new String("극장 전화번호 입력");
        t.setCallNumber(ScannerUtil.nextLine(scanner, message));
        controller.add(t);
    }

    // 극장 수정 메뉴
    public void showUpdate() {
        while (true) {
            ArrayList<TheaterDTO> list = controller.allList();
            System.out.println("====================");
            System.out.println("     극장 정보 수정    ");
            System.out.println("====================");
            for (TheaterDTO t : list) {
                System.out.printf("%d. %s\n", t.getIndex(), t.getName());
            }
            System.out.println("====================");
            String message = new String("수정할 극장 번호 선택(0: 돌아가기)");
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice != 0) {
                TheaterDTO t = controller.selectOne(userChoice);
                while (!(userChoice == 0 || list.contains(t))) {
                    System.out.println("잘못 입력하셨습니다");
                    userChoice = ScannerUtil.nextInt(scanner, message);
                    t = controller.selectOne(userChoice);
                }

                message = new String("극장명 변경");
                t.setName(ScannerUtil.nextLine(scanner, message));

                message = new String("극장 위치 변경");
                t.setLocation(ScannerUtil.nextLine(scanner, message));

                message = new String("극장 전화번호 변경");
                t.setCallNumber(ScannerUtil.nextLine(scanner, message));

                controller.update(t);
                System.out.println("수정 완료");

            } else if (userChoice == 0) {
                System.out.println("상위 메뉴로");
                break;
            }
        }
    }

    // 극장 삭제 메뉴
    public void delete() {
        ArrayList<TheaterDTO> list = controller.allList();
        while (true) {
            if (list.isEmpty()) {
                System.out.println("극장 정보가 모두 삭제되었습니다");
                System.out.println();
                break;
            } else {
                System.out.println("====================");
                System.out.println("    극장 정보 삭제     ");
                System.out.println("====================");
                for (TheaterDTO t : list) {
                    System.out.printf("%d. %s\n", t.getIndex(), t.getName());
                }
                System.out.println("====================");
                String message;
                message = new String("삭제할 극장 번호 선택(0: 뒤로가기)");
                int userChoice = ScannerUtil.nextInt(scanner, message);
                TheaterDTO t = controller.selectOne(userChoice);

                if (userChoice != 0) {
                    while (!(userChoice == 0 || list.contains(t))) {
                        System.out.println("잘못 입력하셨습니다");
                        userChoice = ScannerUtil.nextInt(scanner, message);
                        t = controller.selectOne(userChoice);
                    }
                } else if (userChoice == 0) {
                    System.out.println("상위 메뉴로");
                    break;
                }

                message = new String("정말로 삭제하시겠습니까? y/n");
                String yesNo = ScannerUtil.nextLine(scanner, message);
                if (yesNo.equalsIgnoreCase("y")) {
                    controller.delete(t);
                    System.out.println("★★★★★★★★★★★★★★★");
                    System.out.println("      삭제 완료      ");
                    System.out.println("★★★★★★★★★★★★★★★");
                    System.out.println();
                }
            }
        }
    }

    // infoViewer용
    public TheaterDTO selectOneByIndex(int id) {
        return controller.selectOne(id);
    }

    // infoViewer용
    // 추가 - 유효한 극장 번호인지 확인(컨트롤러 이용)
    public boolean validateTheaterId(int id) {
        return controller.validateIndex(id);
    }

}
