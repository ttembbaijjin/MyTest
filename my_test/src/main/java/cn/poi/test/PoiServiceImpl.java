package cn.poi.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.poi.pojo.Member;

@Service
public class PoiServiceImpl implements PoiService{
	
	@Autowired
	private PoiMapper mapper;

	public List<Member> queryAllMember() {
		try {
			List<Member> list = mapper.queryAllMember();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int saveData(List<Member> list) {
		// TODO Auto-generated method stub
		return 0;
	}

}
