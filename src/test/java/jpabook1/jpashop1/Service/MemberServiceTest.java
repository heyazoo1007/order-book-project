package jpabook1.jpashop1.Service;

import jpabook1.jpashop1.domain.Member;
import jpabook1.jpashop1.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest //스프링부트를 띄운 상태에서 실행(스프링 컨테이너에서 실행)
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{

        Member member = new Member();
        member.setName("yedin");

        Long memberId = memberService.join(member);

        assertEquals(member,memberRepository.findOne(memberId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예제() throws Exception{

        Member member1 = new Member();
        member1.setName("one");

        Member member2 = new Member();
        member2.setName("one");

        memberService.join(member1);
        memberService.join(member2);

        //이 테스트에서는 여기로 오면 안된다
        fail("예외가 발생해야 한다");




    }

}