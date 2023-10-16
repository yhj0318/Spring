package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest // test 케이스 작성
{
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // Test 케이스가 끝날 때마다 클리어 시켜주는 코드
    public void afterEach()
    {
        repository.clearStore();
    }
    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);    // 검증하는 방법
        Member result = repository.findById(member.getId()).get();  // get으로 id를 꺼내올 수 있음
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result); // member가 result와 같은지 판단
        assertThat(member).isEqualTo(result); // 실무에서는 빌드툴이랑 연계해서 오류나면 통과하지 못 함
    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
