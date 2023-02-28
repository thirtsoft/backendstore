package com.wokite.net.backendstore.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class SignUpForm {

    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @Size(max = 60)
    @Email
    private String email;

    @NotNull
    private String[] roles;

    @NotNull
    @Size(min = 6, max = 40)
    private String password;

}
