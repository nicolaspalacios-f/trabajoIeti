package co.edu.escuelaing.IetiLab1.dto;

public class LoginDto {
    String email;

    String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}