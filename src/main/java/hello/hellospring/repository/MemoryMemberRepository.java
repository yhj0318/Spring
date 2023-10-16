package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository // controller-외부요청받고 service-비지니스로직만들고 repository-데이터를저장 정형화된 패턴
public class MemoryMemberRepository implements MemberRepository
{
    private static Map<Long, Member> store = new HashMap<>();   // 실무에서는 동시성 문제가 있어 공유되는 변수일때는 concurrency로 해결
    private static long sequence = 0L;  // 실무에서는 동시성 문제로 atom long
    @Override
    public Member save(Member member)
    {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id)
    {
        return Optional.ofNullable(store.get(id));  // null이 반환될 가능성이 있으면 Optional로 한다
    }
    @Override
    public Optional<Member> findByName(String name)  // 람다로 루프를 돌고 파라미터로 들어온 name이랑 같은지 판단 찾으면 반환하도록 없으면 null
    {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();  // 람다식 많이 씀 루프를 돌기 편해서
    }
    @Override
    public List<Member> findAll() // store의 멤버를 반환
    {
        return new ArrayList<>(store.values());
    }

    public void clearStore()
    {
        store.clear();  // 테스트 케이스 클리어
    }
}
