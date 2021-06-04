package viewer;

import java.util.Scanner;
import controller.UserController;
import model.UserDTO;
import util.ScannerUtil;

public class UserViewer {
    private Scanner scanner;
    private UserController controller;
    private UserDTO logIn;

    public UserViewer() {
        scanner = new Scanner(System.in);
        controller = new UserController();
    }

    public void register(int userChoice) {
        UserDTO u = new UserDTO();
        String message;
        message = new String("사용할 아이디를 입력하세요.");
        String tempId = ScannerUtil.nextLine(scanner, message);
        while (controller.validateUserId(tempId)) {
            System.out.println("중복이거나 사용하실 수 없는 아이디입니다. 다시 확인하세요.");
            message = new String("사용할 아이디를 입력하세요.");
            tempId = ScannerUtil.nextLine(scanner, message);
        }
        u.setUserId(tempId);

        message = new String("사용할 비밀번호를 입력하세요.");
        u.setUserPassword(ScannerUtil.nextLine(scanner, message));

        message = new String("사용할 닉네임을 입력하세요.");
        String tempNickName = ScannerUtil.nextLine(scanner, message);
        while (controller.validateUserId(tempNickName)) {
            System.out.println("중복이거나 사용하실 수 없는 닉네임 입니다. 다시 확인하세요.");
            message = new String("사용할 닉네임을 입력하세요.");
            tempNickName = ScannerUtil.nextLine(scanner, message);
        }
        u.setNickName(tempNickName);

        u.setGroup(userChoice);

        controller.add(u);

    }

    public UserDTO logIn(UserDTO u) {

        UserDTO result = controller.auth(u);
        if (result == null) {
            System.out.println("일치하는 회원정보가 없습니다.");
            return null;
        }
        logIn = result;

        return logIn;
    }

    public void logOut(UserDTO logIn) {

        if (logIn.getGroup() == 1) {
            System.out.println("관람회원 " + logIn.getNickName() + "님 로그아웃 완료");
        } else if (logIn.getGroup() == 2) {
            System.out.println("평론가회원 " + logIn.getNickName() + "님 로그아웃 완료");
        } else if (logIn.getGroup() == 3) {
            System.out.println("관리자" + logIn.getNickName() + "님 로그아웃 완료");
        }

        logIn = null;
    }

    // 현재 로그인 객체의 group을 리턴해주는 메소드
    public int selectGroup() {
        return logIn.getGroup();
    }
}
