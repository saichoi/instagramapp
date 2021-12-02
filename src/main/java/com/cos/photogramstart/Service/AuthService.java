package com.cos.photogramstart.Service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IoC 컨테이너에 띄우고 트렌젝션 관리한다.
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional // Write(inter, update, delete) 시에 붙여주는 어노테이션 
	public User 회원가입(User user) {
		String rawPawssword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPawssword); // 비밀번호를 암호화(해쉬)하여 넣기. 
		user.setPassword(encPassword); // 암호화한 비밀번호를 모델에 담기.
		user.setRole("ROLE_USER"); // 모든 사용자에게 ROLE_USER 권한 부여. 관리자에게는 ROLE_ADMIN으로 권한을 부여할 예정.
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
