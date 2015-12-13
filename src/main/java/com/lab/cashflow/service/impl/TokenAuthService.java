package com.lab.cashflow.service.impl;

import com.lab.cashflow.config.security.SecurityUser;
import com.lab.cashflow.config.security.UserAuthentication;
import com.lab.cashflow.config.security.UserDetailsService;
import com.lab.cashflow.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenAuthService {

    private static final Logger logger = Logger.getLogger(TokenAuthService.class);

    @Value("${jwt.secret}")
    public String TOKEN_SECRET;


    @Autowired
    UserDetailsService userDetailService;

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

    public TokenAuthService() {
        logger.info("TokenAuthService - secret:" + TOKEN_SECRET);
    }

    public void addAuthentication(HttpServletResponse response, Authentication auth) {
        logger.info("addAuthentication - added X-AUTH-TOKEN");
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        response.addHeader(AUTH_HEADER_NAME, this.creatTokenForUser(user));
    }

    /**
     * @param request
     * @return
     */
    public Authentication getAuthentication(HttpServletRequest request) {

        String jwtToken = request.getHeader(AUTH_HEADER_NAME);
        logger.info("getAuthentication - token is :" + jwtToken);

        if (jwtToken == null) {
            return null;
        } else {
            try {
                SecurityUser securityUser = this.parseUserFromToken(jwtToken);
                /*Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
				UsernamePasswordAuthenticationToken UPToken = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword(),user.getAuthorities());
				*/
                UserAuthentication userAuth = new UserAuthentication(securityUser);
                return userAuth;

            } catch (UsernameNotFoundException ex) {
                return null;
            }
        }

    }

    public String creatTokenForUser(User user) {
        String jwtToken = Jwts.builder().setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS512, this.TOKEN_SECRET).compact();
        logger.info("creatTokenForUser - Generated token for user:" + user.getEmail() + " : " + jwtToken);

        return jwtToken;
    }

    public SecurityUser parseUserFromToken(String jwtToken) throws RuntimeException {

        String email = Jwts.parser().setSigningKey(this.TOKEN_SECRET)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();

        logger.info("parseUserFromToken - Parsed jwtToken: " + jwtToken + ", email:" + email);

        SecurityUser user = (SecurityUser) this.userDetailService.loadUserByUsername(email);
        return user;

    }

}
