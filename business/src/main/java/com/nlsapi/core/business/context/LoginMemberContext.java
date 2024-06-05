package com.nlsapi.core.business.context;

import com.nlsapi.core.business.resp.MemberLoginResp;

public class LoginMemberContext {

    private static final ThreadLocal<MemberLoginResp> member = new ThreadLocal<>();

    public static MemberLoginResp getMember() {
        return member.get();
    }

    public static void setMember(MemberLoginResp member) {
        LoginMemberContext.member.set(member);
    }

    public static void removeMember() {
        LoginMemberContext.member.remove();
    }

    public static Long getId() {
        return getMember().getId();
    }

}

