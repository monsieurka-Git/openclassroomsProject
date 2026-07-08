package com.openclassrooms.etudiant.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlacklistService {

    // Ensemble de tokens blacklistés (solution simple en mémoire)
    private final Set<String> blacklistedTokens = new HashSet<>();

    /**
     * Ajoute un token à la blacklist (logout)
     */
    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    /**
     * Vérifie si un token est blacklisté
     */
    public boolean isBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}