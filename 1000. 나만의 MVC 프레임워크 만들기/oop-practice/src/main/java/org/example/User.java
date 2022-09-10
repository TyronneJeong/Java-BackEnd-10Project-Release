package org.example;

public class User {
    private String password;

    public String getPassword() {
        return password;
    }

    public void initPassword(PasswordGenerator passwordGenerator) {
        // 인터페이스 형식으로 교체시 코드간 결합도가 낮아지게 된다.
        // AS-IS
        // RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();

        // TO-BE
        String password = passwordGenerator.generatePassword();

        if(password.length() >= 8 && password.length() <= 12) {
            this.password = password;
        }


    }
}
