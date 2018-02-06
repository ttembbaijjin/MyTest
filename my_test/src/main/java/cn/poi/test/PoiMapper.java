package cn.poi.test;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.xmlbeans.impl.jam.mutable.MMember;

import cn.poi.pojo.Member;

@Mapper
public interface PoiMapper {
	
	List<Member> queryAllMember();
	
	int saveData(List<Member> list);

}
