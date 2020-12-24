package main;

import model.Member;
import model.User;

public class UserFactory implements MemberFactory {
    @Override
    public Member createMember() {
        return new User();
    }
}
