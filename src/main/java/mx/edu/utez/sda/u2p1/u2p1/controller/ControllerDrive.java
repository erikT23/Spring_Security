package mx.edu.utez.sda.u2p1.u2p1.controller;

import mx.edu.utez.sda.u2p1.u2p1.WebSecurityConfig;
import mx.edu.utez.sda.u2p1.u2p1.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerDrive {

    private final PasswordEncoder passwordEncoder;

    public ControllerDrive() {
        passwordEncoder = null;
    }

    @GetMapping("drive")
    @Secured("ROLE_ADMIN")
    public String drive() {


        User user = new User();
        user.setUsername("erik");
        user.setPassword(passwordEncoder.encode("root"));

        return "drive";
    }


}
