package org.example.model;

public class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    // 객체 비교
    // 객체 비교시 객체내 포함된 멤버 변수들의 값이 모두 같으면 해쉬 값 또한 동일해진다.
    public boolean equalsUser(User user){
        return this.equals(user);
    }
}
