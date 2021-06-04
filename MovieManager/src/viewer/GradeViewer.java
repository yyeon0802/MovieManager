package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.GradeController;
import model.GradeDTO;
import model.UserDTO;
import util.ScannerUtil;

// 평점
public class GradeViewer {
    private GradeController controller;
    private Scanner scanner;
    private final int MIN_POINT = 0;
    private final int MAX_POINT = 5;

    public GradeViewer() {
        controller = new GradeController();
        scanner = new Scanner(System.in);
    }

    public void insertGrade(int movieIndex, UserDTO mainLogIn) {
        String message;
        message = new String("1. 평가하기 2. 평가 삭제하기 0. 뒤로가기");
        int userChoice = ScannerUtil.nextInt(scanner, message, 0, 2);
        
        if (userChoice == 1) {
            GradeDTO temp = new GradeDTO();
            
            if (controller.validateByLogInDTO(movieIndex, mainLogIn)) {
                System.out.println("중복 평가 하실 수 없습니다. 해당 영화에 이미 입력하셨습니다.");
                message = new String("0. 뒤로가기");
                userChoice = ScannerUtil.nextInt(scanner, message, 0, 0);
            } else {
                
                message = new String("평점을 입력하세요. (1 ~ 5점까지)");
                temp.setStarPoint(ScannerUtil.nextInt(scanner, message, MIN_POINT, MAX_POINT));
                
                message = new String("한줄평을 입력하세요.");
                temp.setComment(ScannerUtil.nextLine(scanner, message));
                
                temp.setMovieIndex(movieIndex);
                temp.setUserIndex(mainLogIn.getIndex());
                temp.setUserGroup(mainLogIn.getGroup());
                temp.setUserNickname(mainLogIn.getNickName());
                
                controller.add(temp);
                System.out.println("입력이 완료되었습니다.");
            }
        } else if (userChoice == 2) {
            GradeDTO temp = controller.selectGradeOneByLogInDTO(movieIndex, mainLogIn);
            if (temp == null ) {
                System.out.println("등록된 평가가 없어 삭제 불가 합니다.");
            } else {
                if (controller.validateByLogInDTO(movieIndex, mainLogIn)) {
                    message = new String("정말로 삭제 하시겠습니까? Y/N");
                    String yesNo = ScannerUtil.nextLine(scanner, message);
                    if(yesNo.equalsIgnoreCase("Y")) {
                        controller.deleteGradeByIndex(temp);
                        System.out.println(">> 작성하신 평가가 삭제되었습니다.");
                    }
                } else {
                    System.out.println("본인이 작성하신 평가만 삭제 가능합니다.");
                }
            }
        }
    }

    public void showGrade(int movieIndex) {
        ArrayList<GradeDTO> temp1 = controller.group1List(movieIndex);
        ArrayList<GradeDTO> temp2 = controller.group2List(movieIndex);

        if (temp1.isEmpty()) {
            System.out.println("----------------------------------");
            System.out.println("등록된 관람객 평가가 없습니다.");
        } else {
            System.out.println("----------------------------------");
            System.out.println(">>>> 관람객 평가");
            System.out.println("----------------------------------");
            for (GradeDTO g : temp1) {
                System.out.printf("<%s님> 별점 : %d개 한줄평 : %s \n", g.getUserNickname(), g.getStarPoint(), g.getComment());
            }
        }

        if (temp2.isEmpty()) {
            System.out.println("----------------------------------");
            System.out.println("등록된 평론가 평가가 없습니다.");
            System.out.println("----------------------------------");
        } else {
            System.out.println("----------------------------------");
            System.out.println(">>>> 평론가 평가");
            System.out.println("----------------------------------");
            for (GradeDTO g : temp2) {
                System.out.printf("<%s님> 별점 : %d개 한줄평 : %s \n", g.getUserNickname(), g.getStarPoint(), g.getComment());
            }
            System.out.println("----------------------------------");
        }
        
        if (controller.totalPoint(movieIndex) > 0.0) {
            System.out.printf(">>> 전체 별점 : %.1f개\n", controller.totalPoint(movieIndex));
            System.out.println("==================================");
        } else {
            System.out.println("전체 평가가 없습니다.");
            System.out.println("==================================");
        }
        
    }
       
}
