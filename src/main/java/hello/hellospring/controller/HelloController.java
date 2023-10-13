package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data", "hello!!"); //value가 hello.html의 {data}로 치환 됌
        return "hello"; //templates의 hello로 접근
    }

    @GetMapping("hello-mvc")    // model view controll
    public String helloMvc(@RequestParam("name") String name, Model model)
    {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // html body문에 직접 넣겠다는 의미
    public String helloString(@RequestParam("name") String name)
    {
        return "hello" + name;  //"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello
    {
        private String name;
        public String getName()
        {
            return name;
        }
        public void setName(String name)
        {
            this.name = name;
        }
    }
}