package hello.helloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        // model로 html에 값을 전달해줌
        model.addAttribute("data", "Hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 데이터만 내려줌. 거의 쓸일이 없음
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name") String name) {
        return "hello " + name; // "hello spring"
    }

    // key : value 인 Json 느낌으로 나옴.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // getter, setter, property 접근 방식.
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
