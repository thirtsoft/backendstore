package com.wokite.net.backendstore.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class LoginForm {

    @NotNull
    @Size(min = 3, max = 60)
    private String username;

    @NotNull
    @Size(min = 6, max = 40)
    private String password;

}
