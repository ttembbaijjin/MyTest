package cn.pessimisticLock;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.optimisticLock.User;

@Mapper
public interface PessimisticLockMapper {
	
	int update(@Param("user") User user);
	
	User getUserById(User user);
}
