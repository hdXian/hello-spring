package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // get 요청 mapping annotation
    public String hello(Model model){
        model.addAttribute("data", "unknown"); // hello.html에 전달할 데이터
        return "hello"; // hello.html로 연결. Controller에서 문자열 값을 반환하면 viewResolver가 화면을 찾아서(hello.html) 처리한다.
    }
}
