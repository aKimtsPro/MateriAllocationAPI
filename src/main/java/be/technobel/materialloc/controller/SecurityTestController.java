package be.technobel.materialloc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security/test")
public class SecurityTestController {


    @PreAuthorize("permitAll()")
    @GetMapping("/permit-all")
    public String permitAll(){
        return "ok";
    }


    @GetMapping("/connected")
    public String connected(Authentication auth){
        return auth.getPrincipal().toString();
    }

    @GetMapping("/not-connected")
    public String notConnected(){
        return "ok";
    }

    @GetMapping("/role_user")
    public String hasRoleUser(){
        return "ok";
    }

    @GetMapping("/any_role")
    public String hasAnyRoleUserAdmin(){
        return "ok";
    }

    @GetMapping("/has_authority_role_user")
    public String hasAuthority(){
        return "ok";
    }

    @GetMapping("/has_any_authority")
    public String hasAnyAuthority(){
        return "ok";
    }

}
