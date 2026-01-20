package com.devops.cicd.user;

public class UserService {

    public User register(String email, String password, Role role) {
        // Crée un User (les validations sont faites dans le constructeur de User)
        // Propage les IllegalArgumentException si les données sont invalides
        return new User(email, password, role);
    }
}