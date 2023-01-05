package com.example.question_system.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.question_system.entity.UserInfo;


@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {

	// ��X�^���P�@�D�D�ت��ϥΪ� ��list�h�]
	public List<UserInfo> findAllByQuestionNumber(Integer questionNumber);

	public Optional<UserInfo> findByQuestionNumberAndUserNameAndUserPhone(Integer questionNumber, String userName,
			String userPhone);
}
