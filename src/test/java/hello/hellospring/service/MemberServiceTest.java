package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest
{
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach // Defendency Injection DI
    public void beforeEach()
    {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach  // Test 케이스가 끝날 때마다 클리어 시켜주는 코드
    public void afterEach()
    {
        memberRepository.clearStore();
    }
    
    @Test
    void 회원가입() // 테스트에서는 한글로 작성해도 됌
    {
        //given 무언가가 주어지면
        Member member = new Member();
        member.setName("hello");

        //when  이거를 실행했을 때
        Long saveId = memberService.join(member);

        //then  이게 나와야 돼
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외()
    {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try
        {
            memberService.join(member2); // validate에서 예외가 터진다
            fail();
        }
        catch(IllegalStateException e)
        {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }

    @Test
    void findMembers()
    {

    }

    @Test
    void findOne()
    {

    }
}