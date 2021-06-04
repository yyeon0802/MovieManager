package viewer;

import java.util.Scanner;

import model.UserDTO;
import util.ScannerUtil;

// 처음 메뉴
public class MainViewer {
    private Scanner scanner;
    private UserViewer userViewer;
    private InfoViewer infoViewer;
    private MovieViewer movieViewer;
    private TheaterViewer theaterViewer;
    private GradeViewer gradeViewer;
    private UserDTO mainLogIn;

    public MainViewer() {
        scanner = new Scanner(System.in);
        userViewer = new UserViewer();
        theaterViewer = new TheaterViewer(userViewer);
        movieViewer = new MovieViewer();
        gradeViewer = new GradeViewer();
        infoViewer = new InfoViewer(movieViewer, theaterViewer);
    }

    public void showIndex() {
        while (true) {
            String message = new String("1. 회원가입 2. 로그인 0. 종료");
            int userChoice = ScannerUtil.nextInt(scanner, message, 0, 2);

            // 회원가입
            if (userChoice == 1) {
                SignIn();

            } else if (userChoice == 2) {
                LogIn();

            } else if (userChoice == 0) {
                LogOut();
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
    }

    // 회원 가입
    private void SignIn() {
        while (true) {
            System.out.println("가입할 유형에 해당하는 번호를 입력하세요.");
            String message = new String("1. 관람객 2. 평론가  3. 관리자  0. 돌아가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 0, 3);
            if (userChoice == 0) {
                System.out.println("상위 메뉴로");
                break;
            } else {
                userViewer.register(userChoice);
                break; // hj
            }
        }
    }

    // 로그인
    private void LogIn() {
        while (true) {
            String message;
            UserDTO temp = new UserDTO();

            // hj
            String yesNo = new String();
            // message = new String("로그인을 진행합니다. 중지하고 뒤로 가시겠습니까? Y/N ");
            // String yesNo = ScannerUtil.nextLine(scanner, message);
            //
            // if (yesNo.equalsIgnoreCase("Y")) {
            // break;
            // }

            message = new String("[로그인] 아이디를 입력하세요.");
            temp.setUserId(ScannerUtil.nextLine(scanner, message));
            message = new String("[로그인] 비밀번호를 입력하세요.");
            temp.setUserPassword(ScannerUtil.nextLine(scanner, message));

            if (userViewer.logIn(temp) != null) {
                mainLogIn = userViewer.logIn(temp);
                if (mainLogIn.getGroup() == 1) {
                    System.out.println("관람회원> " + mainLogIn.getNickName() + "님 로그인 완료");
                    showMenu();
                    break; // hj
                } else if (mainLogIn.getGroup() == 2) {
                    System.out.println("평론가회원> " + mainLogIn.getNickName() + "님 로그인 완료");
                    showMenu();
                    break; // hj
                } else if (mainLogIn.getGroup() == 3) {
                    System.out.println("관리자> " + mainLogIn.getNickName() + "님 로그인 완료");
                    showMenu();
                    break; // hj
                }
            } else {
                System.out.println("일치하는 정보가 없습니다.");
                message = new String("계속 로그인을 진행하시려면 Y / 중지하시고 뒤로 가시려면 N 을 입력하세요.");
                yesNo = ScannerUtil.nextLine(scanner, message);

                if (yesNo.equalsIgnoreCase("N")) {
                    break;
                }
            }
        }
    }
    
    private void LogOut() {
        System.out.println(mainLogIn.getNickName() + "님 , 로그아웃 되셨습니다.");
        mainLogIn = null;
    }

    private void showMenu() {
        while (true) {
            String message = new String("1. 영화 목록 보기 2. 극장 목록 보기 3. 상영 정보 보기 0. 돌아가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 0, 3);
            if (userChoice == 1) {
                movieViewer.showMovieList();

                if (mainLogIn.getGroup() != 3) {
                    message = new String("세부정보를 열람할 영화 번호를 입력하세요.");
                    userChoice = ScannerUtil.nextInt(scanner, message);
                    movieViewer.printOneMovieByIndex(userChoice);
                    if(movieViewer.validateMovieId(userChoice)) {
                        gradeViewer.showGrade(userChoice);
                        gradeViewer.insertGrade(userChoice, mainLogIn);
                    }
                } else {
                    MovieAdminMenu();
                }

            } else if (userChoice == 2) {
                // 극장 목록
                theaterViewer.setInfoViewer(infoViewer);
                theaterViewer.setMovieViewer(movieViewer);
                theaterViewer.theaterList();

            } else if (userChoice == 3) {
                // 상영정보
                showMovieInfo();

            } else if (userChoice == 0) {
                System.out.println("<회원가입 / 로그인> 메뉴로 돌아갑니다.");
                break;
            }
        }
    }

    public void MovieAdminMenu() {
        String message;
        message = new String("1.영화 입력하기 2.영화 수정하기 3.영화 삭제하기 4.세부정보 보기 0.뒤로가기");
        int userChoice = ScannerUtil.nextInt(scanner, message, 0, 4);
        if (userChoice == 1) {
            movieViewer.newMovieInput();
        } else if (userChoice == 2) {
            movieViewer.movieUpdate();
        } else if (userChoice == 3) {
            movieViewer.movieDelete();

        } else if (userChoice == 4) {
            message = new String("세부정보를 열람할 영화 번호를 입력하세요.");
            userChoice = ScannerUtil.nextInt(scanner, message);
            movieViewer.printOneMovieByIndex(userChoice);
            if(movieViewer.validateMovieId(userChoice)) {
                gradeViewer.showGrade(userChoice);                
            }
        }
        System.out.println("<목록 / 정보보기> 메뉴로 돌아갑니다.");
    }

    private void showMovieInfo() {

        while (true) {
            System.out.println("================= 상영정보 =================");
            String message = new String();
            // 먼저 상영정보를 전부 보여주기
            // 어떤 값이 존재하면 출력 아니면 없다는 메시지 띄우기
            infoViewer.printLIst();

            System.out.println("==========================================");

            int userChoice = 0;
            if (mainLogIn.getGroup() == 3) {
                // 관리자
                message = new String("1. 상영정보 등록하기 2. 상영정보 수정하기 \n3. 상영정보 삭제하기 0. 돌아가기");
                userChoice = ScannerUtil.nextInt(scanner, message, 0, 3);

                if (userChoice == 1) {

                    infoViewer.registerMovieInfo();
                    // 상영번호 중복되면 통과되면 안되는데 통과됨,,

                } else if (userChoice == 2) {
                    infoViewer.updateMovieInfo();

                } else if (userChoice == 3) {
                    infoViewer.deleteMovieInfo();

                } else {
                    System.out.println();
                    System.out.println("<목록 / 정보보기> 메뉴로 돌아갑니다.");
                    break;
                }

            } else {
                // 일반사용자
                message = new String("0.돌아가기");
                ScannerUtil.nextInt(scanner, message);
                break; // hj

            }
        }
    }
}
