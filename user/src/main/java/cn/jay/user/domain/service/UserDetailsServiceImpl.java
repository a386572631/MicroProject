package cn.jay.user.domain.service;

import cn.jay.user.domain.repository.mapper.UserMapper;
import cn.jay.user.domain.repository.po.UserPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "UserDetailsServiceImpl")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserPo> user = userMapper.getUserRole(username);
        log.info("user: " + user);
        if (user.size() < 1) {
            return null;
        }
        List<String> roles = user.stream().map(UserPo::getRole).collect(Collectors.toList());
        List<GrantedAuthority> list = new ArrayList<>();
        roles.stream().forEach(e -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(e);
            list.add(authority);
        });
        return User.withUsername(user.get(0).getUsername()).password(user.get(0).getPassword())
                .authorities(list).disabled("0".equals(user.get(0).getState()) ? true : false).build();
    }
}