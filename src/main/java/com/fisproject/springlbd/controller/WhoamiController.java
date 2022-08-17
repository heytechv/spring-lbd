package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.apiresponse.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhoamiController {

    @GetMapping("/whoami")
    public ResponseEntity<StandardResponse> getLoggedUser() {
        /* Zad 22 */
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        return new StandardResponse( HttpStatus.OK, auth.getName() + " " + auth.getAuthorities(), "Logged in user")
                .buildResponseEntity();
    }

}
