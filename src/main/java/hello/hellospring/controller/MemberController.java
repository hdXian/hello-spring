package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller// 해당 어노테이션이 붙어있으면 스프링 컨테이너에서 객체를 생성하여 관리함.
public class MemberController {

    private final MemberService memberService; //     (4-1)MemberService와 같은 객체는 여러곳에서 쓰기 때문에 객체를 각각 만들어 쓰기에 적절치 않다.
    // @Autowired private MemberService memberService (4-2) @Autowired를 선언부에 적어 의존관계를 자동으로 설정하는 필드 주입 DI 방식

    /* (4-2) setter를 통해 의존 객체를 전달하는 setter 주입 DI 방식. setter가 public으로 공개된다는 단점이 있다.
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */

    // (4-2)생성자를 통해 의존 객체를 전달하는 생성자 주입 DI 방식. 권장되는 방식이다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // (5-2)클라이언트의 Get 요청을 받아 처리.
    public String createForm() {
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new") // (5-2)클라이언트의 Post 요청을 받아 처리.
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // (5-2)홈 화면으로 돌아감.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // (5-3)model에 List<Member>를 담아 전달.

        return "/members/memberList";
    }
}
