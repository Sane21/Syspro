package com.syspro.booksns.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syspro.booksns.model.PastBookInfo;
import com.syspro.booksns.repository.PastBookInfoMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class PastBookInfoService {
	private final PastBookInfoMapper mapper;

	public List<PastBookInfo> selectAll(){
		return mapper.selectAll();
	}
	
	public PastBookInfo selectByPrimaryKey(Long pastBookInfoId) {
		return mapper.selectByPrimaryKey(pastBookInfoId);
	}
	
	public void save(PastBookInfo pastBookInfo) {
		if(pastBookInfo.getPastBookInfoId() == null) mapper.insert(pastBookInfo);
		else mapper.updateByPrimaryKey(pastBookInfo);
	}
	
	public int deleteByPrimaryKey(Long pastBookInfoId) {
		return mapper.deleteByPrimaryKey(pastBookInfoId);
	}
}
