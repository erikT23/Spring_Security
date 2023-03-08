package mx.edu.utez.sda.u2p1.u2p1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerDrive {

    @GetMapping("drive")
    public String drive(){
        return "drive";
    }
}
