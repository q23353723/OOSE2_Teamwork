package main;

import model.Admin;
import model.Member;

public class AdminFactory implements MemberFactory {
    @Override
    public Member createMember() {
        return new Admin();
    }
}
