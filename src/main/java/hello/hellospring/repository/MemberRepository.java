package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository
{
    Member save(Member member);
    Optional<Member> findById(Long id); // id로 회원찾기
    Optional<Member> findByName(String name);   // 없으면 null 반환하는 방법
    List<Member> findAll(); // 모든 회원 리스트를 반환
}
