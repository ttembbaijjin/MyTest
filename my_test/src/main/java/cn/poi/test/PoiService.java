package cn.poi.test;

import java.util.List;

import cn.poi.pojo.Member;

public interface PoiService {
	
	List<Member> queryAllMember();
	
	int saveData(List<Member> list);

}
