package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController
{
    private final MemberService memberService;

    @Autowired  //controller랑 service를 연결해주는 역할 DI
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }
}
