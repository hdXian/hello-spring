package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // Member를 저장할 자료구조
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // member의 id를 지정
        store.put(member.getId(), member); // store에 member 저장
        return member; // 저장된 member 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store에서 id를 이용해 member를 찾아 반환. null일 경우를 대비하여 Optional.ofNullable()로 감싸 반환한다. 이렇게 반환하면 null이 반환돼도 클라에서 대응 가능하다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store의 values(member들)의 스트림을 열고
                .filter(member -> member.getName().equals(name)) // 각 member의 name 중에서 매개변수 name과 같은게 있으면
                .findAny(); // 찾아서 반환한다. Optional<Member>로 반환됨. 찾으면 찾는대로, 없으면 Optional로 null 처리해서 반환.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store의 value들을 요소로 가지는 ArrayList 반환.
    }

    public void clearStore() {
        store.clear();
    }
}
