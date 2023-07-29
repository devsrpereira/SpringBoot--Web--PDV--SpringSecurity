package com.srdevepereira.pdv.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "O campo nome é obrigatório")
    private String name;

    @NotBlank(message = "O campo username é obrigatório")
    private String username;

    @NotBlank(message = "O campo senha é obrigatório")
    private String password;

    private boolean isEnabled;
}
