package com.woniuxy.springboot.HIS.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.woniuxy.springboot.HIS.entity.Pharmacykc;
/**
 * 库存操作mapper
 * @author LG
 *
 */
public interface PharmacykcMapper {
		
	
		//新增库存条目
		void insertPharmacykc(Pharmacykc pharmacykc);
		//查询库存通过mid
		Pharmacykc selectPharmacykcByMid(Integer mid);
		//按查询所有库存
		List<Pharmacykc> selectAllPharmacykc();
		//查询不足库存
		List<Pharmacykc> selectPharmacykcByYfkcnum();
		//修改库存数量通过mid
		void updatePharmacykcByMid
		(@Param("yfkcnum") Integer yfkcnum,@Param("mid")Integer mid);
		//删除库存通过mid
		void deletePharmacykcByMid(Integer mid);
		//通过名字查询，用于分页
		List<Pharmacykc> selectPharmacykcByYfkcnumAndMname(String mname);
		//通过名字查全部
		List<Pharmacykc> selectPharmacykcByMname(String mname);
		
}
