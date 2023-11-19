package br.com.itneki.NekiSkills.dto;

import br.com.itneki.NekiSkills.domain.UserRole;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SignUpDTO {
    private String login;
    private String password;
}
