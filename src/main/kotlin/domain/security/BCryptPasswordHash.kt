package com.example.domain.security

import org.mindrot.jbcrypt.BCrypt

class BCryptPasswordHash : PasswordHashInterface {
    override fun hash(pass: String): String {
        return BCrypt.hashpw(pass, BCrypt.gensalt()) // Genera hash válido
    }

    override fun verify(pass: String, passHash: String): Boolean {
        return try {
            BCrypt.checkpw(pass, passHash)
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}