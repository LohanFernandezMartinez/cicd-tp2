package com.devops.cicd.user;

public final class EmailValidator {

    private EmailValidator() {}

    public static boolean isValid(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String trimmedEmail = email.trim();

        // Règle : exactement un seul caractère '@'
        int firstAt = trimmedEmail.indexOf('@');
        int lastAt = trimmedEmail.lastIndexOf('@');
        if (firstAt == -1 || firstAt != lastAt) {
            return false;
        }

        // Règle : au moins un '.' après le '@'
        String domainPart = trimmedEmail.substring(firstAt + 1);
        return domainPart.contains(".") && !domainPart.startsWith(".") && !domainPart.endsWith(".");
    }
}
