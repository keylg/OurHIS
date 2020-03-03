package com.woniuxy.springboot.HIS;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woniuxy.springboot.HIS.entity.Doctor;
import com.woniuxy.springboot.HIS.entity.Hospitals;
import com.woniuxy.springboot.HIS.entity.Keshi;
import com.woniuxy.springboot.HIS.entity.Title;
import com.woniuxy.springboot.HIS.mapper.DoctorMapper;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorMapperTests {
	@Autowired
	DoctorMapper doctorMapper;
	
	@Autowired
	DataSource dataSource;
	
	
	@Test
	//测试添加医生
	public void testInsertDoctor() {
		Doctor doctor = new Doctor(2,"周杰","3122","nan","sc",31,"110",
				new Title(1,"专家"),new Hospitals(1,"11", 1),new Keshi(1, null, "1",11, null));
		doctorMapper.insertDoctor(doctor);
	}
	
	@Test
	//测试查询医生
	public void testSelectDoctorByTid() {
		Doctor doctor = doctorMapper.selectDoctorByTid(1);
		System.out.println(doctor);
	}
	
	@Test
	//测试更新医生
	public void testUpdateDoctorByDid() {
		Doctor doctor = new Doctor(1,"光头强","3122","nan","sc",31,"110",
				new Title(1,"专家"),new Hospitals(1,"11", 1),new Keshi(1, null, "1",11, null));
		doctorMapper.updateDoctorByTid(1, doctor);
	}
}
