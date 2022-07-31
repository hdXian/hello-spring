package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 루트 디렉터리. 처음 들어갈 때 나오는 홈 위치.
    public String home() {
        return "home";
    }
}
