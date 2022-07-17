package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // get 요청 mapping annotation
    public String hello(Model model){
        model.addAttribute("data", "unknown"); // hello.html에 전달할 데이터
        return "hello"; // hello.html로 연결. Controller에서 문자열 값을 반환하면 viewResolver가 화면을 찾아서(hello.html) 처리한다.
    }

    @GetMapping("hello_mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // 웹페이지 요청 시 인자를 전달받는다.
        model.addAttribute("name", name);
        return "hello_template";
    }

    @GetMapping("hello_string") // 웹 브라우저에서 요청할 때 입력하는 값 ex) https://localhost:8080/hello_string?name="spring"
    @ResponseBody // http 통신의 바디 부분에 데이터를 넣는다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 리턴값이 클라이언트에 그대로 전달됨 -> 클라이언트에서 데이터만 받고 화면 구성
    }

    @GetMapping("hello_api")
    @ResponseBody
    public HelloData helloApi(@RequestParam("name") String name) {
        HelloData hd = new HelloData();
        hd.setName(name);
        return hd; // 객체 데이터를 넘긴다. 데이터는 json 방식으로 넘어감. K-V 방식. ex) { "name" : "spring" }
    }

    static class HelloData {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }



    }
}
