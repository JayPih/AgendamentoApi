package com.example.agendamentoapp.dto;

import com.example.agendamentoapp.enums.UserRole;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterDTO {
    private String login;
    private String password;
    private UserRole role;
}
