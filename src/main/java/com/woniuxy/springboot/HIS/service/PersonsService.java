package com.woniuxy.springboot.HIS.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.woniuxy.springboot.HIS.entity.Persons;

public interface PersonsService {
	/**
	 * 新添加患者
	 * @param persons 需要新增的患者
	 */
	void insertPersons(Persons persons);
	/**
	 * 查找患者
	 * @param persons 查找的条件
	 * @return 按条件查找到到的所有患者
	 */
	List<Persons> selectPersons(@Param("persons")Persons persons);
	/**
	 * 根据pid查找患者
	 * @param pid 需要查找的患者id
	 * @return
	 */
	Persons selectPersonsByPid(Integer pid);
	/**
	 * 更新患者信息
	 * 	 * @param persons
	 */
	void updatePersonsByPid(Persons persons);
	/**
	 * 用医生的id查询他负责的患者
	 * @param tid
	 * @param date
	 * @return
	 */
	PageInfo<Persons> selectAllPersonsBytid(Integer tid,Integer pageno,Integer pagesize);
}
