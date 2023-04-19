package com.gitaeklee.invest.repository.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {
    private String username;
    private String email;
    private String password;
}