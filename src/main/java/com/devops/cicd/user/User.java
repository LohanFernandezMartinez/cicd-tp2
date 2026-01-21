package com.devops.cicd.user;

import com.devops.cicd.PasswordPolicy;
import static com.devops.cicd.user.Role.ADMIN;

public final class User {

    private final String email;
    private final String password;
    private final Role role;

    public User(String email, String password, Role role) {
        // Validation de l'email
        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("email must be valid");
        }

        // Validation du password
        if (!PasswordPolicy.isStrong(password)) {
            throw new IllegalArgumentException("password must be strong");
        }

        // Validation du rôle
        if (role == null) {
            throw new IllegalArgumentException("role must not be null");
        }

        // Normalisation : email est trim() avant stockage
        this.email = email.trim();
        // Le password n'est pas modifié
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean canAccessAdminArea() {
        // Retourne true si et seulement si role == ADMIN
        return this.role == ADMIN;
    }
}