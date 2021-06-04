package controller;

import java.util.ArrayList;
import model.UserDTO;

public class UserController {
    ArrayList<UserDTO> list;
    int index;

    public UserController() {
        list = new ArrayList<>();
        index = 1;

        UserDTO u = new UserDTO();
        u.setIndex(1);
        u.setGroup(1);
        u.setNickName("일반1");
        u.setUserId("a1");
        u.setUserPassword("1");
        list.add(u);

        UserDTO u1 = new UserDTO();
        u1.setIndex(2);
        u1.setGroup(1);
        u1.setNickName("일반2");
        u1.setUserId("a2");
        u1.setUserPassword("2");
        list.add(u1);

        UserDTO u2 = new UserDTO();
        u2.setIndex(3);
        u2.setGroup(2);
        u2.setNickName("평론1");
        u2.setUserId("b1");
        u2.setUserPassword("1");
        list.add(u2);

        UserDTO u3 = new UserDTO();
        u3.setIndex(4);
        u3.setGroup(2);
        u3.setNickName("평론2");
        u3.setUserId("b2");
        u3.setUserPassword("2");
        list.add(u3);

        UserDTO u4 = new UserDTO();
        u4.setIndex(5);
        u4.setGroup(3);
        u4.setNickName("관리자");
        u4.setUserId("c1");
        u4.setUserPassword("1");
        list.add(u4);
    }

    public UserDTO auth(UserDTO user) {
        for (UserDTO u : list) {
            if (u.getUserId().equals(user.getUserId()) && u.getUserPassword().equals(user.getUserPassword())) {
                return u;
            }
        }
        return null;
    }

    public void add(UserDTO user) {
        user.setIndex(index++);
        list.add(user);
    }

    public boolean validateUserId(String userId) {
        for (UserDTO user : list) {
            if (user.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    // 로그인한 회원의 그룹 id 받아 반환
    public UserDTO selectGroup(int id) {
        for (UserDTO u : list) {
            if (u.getGroup() == id) {
                return u;
            }
        }
        return null;
    }
}
