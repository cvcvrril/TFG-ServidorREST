package com.example.servidorrestinesmr.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PublicKey;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${application.security.keystore.path}")
    private String path;
    @Value("${application.security.keystore.password}")
    private String password;
    @Value("${application.security.keystore.userkeystore}")
    private String userkeystore;

    public boolean validate(String token) throws ExpiredJwtException, SignatureException {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token);

        long expirationMillis = claimsJws.getBody().getExpiration().getTime();
        return System.currentTimeMillis() < expirationMillis;

    }

    public String getUsername(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    public String getRol(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token);
        return claimsJws.getBody().get("rol", String.class);
    }

    private PublicKey getPublicKey() {
        try {
            KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
            try (FileInputStream fis = new FileInputStream(path)) {
                ks.load(fis, password.toCharArray());
            }
            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(password.toCharArray());
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(userkeystore, pt);
            if (pkEntry != null) {
                return pkEntry.getCertificate().getPublicKey();
            } else {
                throw new RuntimeException("No se encontró la entrada de la clave privada en la keystore");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la clave privada de la keystore");
        }
    }
}
