package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Member;

public class MemberDao extends Dao {
	private List<Member> members;

	public MemberDao() {
		this.members = new ArrayList<>();

	}

	public void add(Member member) {
		members.add(member);
		this.lastId++;
	}

	// 해당 아이디가 멤버스에 있으면 해당 하는 아이디가 있는 방을 넘겨주는 함수
	public Member getMembeByLoginId(String loginID) {
		for (Member member : members) {
			if (member.LoginID.equals(loginID)) {
				return member;
			}
		}
		return null;
	}

	public String getWriterName(int membeId) {
		for (Member member : members) {
			if (membeId == member.id) {
				return member.name;
			}
		}
		return null;
	}

}
