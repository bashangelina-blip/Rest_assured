package testdata;

import dto.user.User;

public class UserFactory {
    static String username = "Momo";
    static String password = "hello123";

    public static User defaultUser() {
        return User.builder()
                .id(System.currentTimeMillis())
                .username(username)
                .password(password)
                .email("test@gmail.com")
                .phone("123654")
                .firstName("Olha")
                .lastName("Bubuk")
                .userStatus(1)
                .build();
    }
}
