package gos.fdc.cap.dto;

import lombok.Data;

@Data
public class UserForm {
    private String username;
    private String tel;
    private String email;
    private String password;
    private String confirmedPassword;
}
