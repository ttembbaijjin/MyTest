package cn.optimisticLock;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyMapper {
	
	int updateUser(@Param("user") User user);
	
	User getUserById(User user);

}
