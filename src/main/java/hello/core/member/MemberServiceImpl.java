package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
//관례 : 인터페이스의 구현체가 하나뿐인 경우 Impl로 축약하여 적는다
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
/*  이제 유일한 생성자 대신 lombok의 @RequiredArgsConstructor를 사용합니다.
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
