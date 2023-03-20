package be.technobel.materialloc.config.security;

import be.technobel.materialloc.models.entity.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;

    public JwtProvider(JwtProperties jwtProperties, UserDetailsService userDetailsService) {
        this.jwtProperties = jwtProperties;
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(Authentication auth){

        return jwtProperties.getPrefix() + JWT.create()
                .withExpiresAt( Instant.now().plusMillis(jwtProperties.getExpiresAt()) )
                .withSubject(auth.getName())
                .withClaim("role", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null))
                .sign( Algorithm.HMAC512(jwtProperties.getSecret()) );

    }


    public String extractToken(HttpServletRequest req){
        String authHeader = req.getHeader( jwtProperties.getAuthHeader() );

        if(authHeader == null || !authHeader.startsWith( jwtProperties.getPrefix() ))
            return null;

        return authHeader.replaceFirst(jwtProperties.getPrefix(), "" );
    }

    public boolean validateToken(String token){

        try {
            // 1, Le bon secret a été utilisé (et le meme algo)
            // 2, pas expiré
            DecodedJWT jwt = JWT.require( Algorithm.HMAC512(jwtProperties.getSecret()) )
                    .acceptExpiresAt( jwtProperties.getExpiresAt() )
                    .withClaimPresence("sub")
                    .withClaimPresence("role")
                    .build()
                    .verify( token );

            // 3, généré a partir d'un user existant
            String username = jwt.getSubject();
            User user = (User) userDetailsService.loadUserByUsername(username);
            if( !user.isEnabled() )
                return false;

            // (4, Les roles ne sont plus bon) Verifier les roles n'est pas conventionnel
            String tokenRole = jwt.getClaim("roles").asString();
            return Objects.equals(user.getRole(),  tokenRole);
        }
        catch (JWTVerificationException | UsernameNotFoundException ex ){
            return false;
        }

    }

    public Authentication createAuthentication(String token){
        DecodedJWT jwt = JWT.decode(token);

        String username = jwt.getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }
}
