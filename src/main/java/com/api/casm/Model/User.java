package com.api.casm.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "the field 'name' cannot be blank")
    @Column(name = "nombre", length = 45)
    private String name;
    @NotBlank(message = "the field 'userName' cannot be blank")
    @Column(name = "nombre_usuario")
    @Size(min = 4, max = 20)
    private String userName;
    @NotBlank(message = "the field 'email' cannot be blank")
    @Email(message = "The field 'email' must contain a valid email address.")
    @Column(name = "correo", length = 45)
    private String email;
    @NotBlank(message = "the field 'password' cannot be blank")
    @Column(name = "contraseña")
    @Size(min = 8, max = 20)
    private String password;
}
