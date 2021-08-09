package cn.jay.authentication.domain.repository.mapper;

import cn.jay.authentication.domain.repository.po.UserPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UserPo> {
    List<UserPo> getUserRole(String username);
}
