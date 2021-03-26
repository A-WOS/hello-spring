package hello.helloSpring.controller;

import hello.helloSpring.domain.Member;
import hello.helloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 필드 주입
//    @Autowired private MemberService memberService;
    private final MemberService memberService;

    // set 주입
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 생성자 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    // @GetMapping은 조회할 때 쓰이고
//    // @PostMapping은 값을 넣을 때 쓰임
//    @GetMapping("/members/new")
//    public String createForm() {
//        return "members/createMemberForm";
//    }
//
//    @PostMapping("/members/new")
//    public String create(MemberForm form) {
//        Member member = new Member();
//        member.setName(form.getName());
//        //soutv
//        System.out.println("member.getName() = " + member.getName());
//        memberService.join(member);
//
//        return "redirect:/";
//    }
//
//    @GetMapping("/members")
//    public String list(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//
//        return "members/memberList";
//    }
}
