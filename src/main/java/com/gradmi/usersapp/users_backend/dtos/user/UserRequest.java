package com.gradmi.usersapp.users_backend.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank
    String name,

    @NotBlank
    String lastName,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = 6, max = 12)
    String username,

    Boolean isAdmin
) implements IUser {

}
