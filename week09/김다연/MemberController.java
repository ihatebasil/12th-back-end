package hello.helloSpring.controller;

import hello.helloSpring.aspect.LogExecutionTime;
import hello.helloSpring.domain.Member;
import hello.helloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController  {
    private final MemberService memberService;

    @Autowired
    //연결시켜줄때 오토와이어드
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @LogExecutionTime
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @LogExecutionTime
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
    @LogExecutionTime
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
