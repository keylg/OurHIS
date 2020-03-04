package com.woniuxy.springboot.HIS.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.woniuxy.springboot.HIS.entity.Doctor;
import com.woniuxy.springboot.HIS.entity.Historicalpatients;
import com.woniuxy.springboot.HIS.entity.Keshi;
import com.woniuxy.springboot.HIS.entity.Paydetail;
import com.woniuxy.springboot.HIS.entity.Paytype;
import com.woniuxy.springboot.HIS.entity.Persons;
import com.woniuxy.springboot.HIS.entity.Totalpay;
import com.woniuxy.springboot.HIS.service.DoctorService;
import com.woniuxy.springboot.HIS.service.HistoricalpatientsService;
import com.woniuxy.springboot.HIS.service.KeshiService;
import com.woniuxy.springboot.HIS.service.PaydetailService;
import com.woniuxy.springboot.HIS.service.PersonsService;
import com.woniuxy.springboot.HIS.service.RegisterService;
import com.woniuxy.springboot.HIS.service.TotalpayService;
import com.woniuxy.springboot.HIS.service.impl.TotalpayServiceImpl;

import net.sf.jsqlparser.util.AddAliasesVisitor;


@Controller
public class RegisterController {
	@Autowired
	RegisterService registerService;
	@Autowired
	PersonsService personsService;
	@Autowired
	TotalpayService totalService;
	@Autowired
	PaydetailService paydetailService;
	@Autowired
	KeshiService keshiService;
	@Autowired
	DoctorService doctorService;
	@Autowired
	HistoricalpatientsService historicalpatientsService;
	/**
	 * 新患者挂号
	 */
	@Transactional
	@RequestMapping("/register/newpersonsRegister")
	public String newPersonsRegister(Model model,Persons persons,Integer did,Integer kid) {
		//添加患者信息
		personsService.insertPersons(persons);
		//查找出刚添加患者的主键
		persons= personsService.selectPersons(persons).get(0);
		//根据id查找出科室
		Keshi keshi = keshiService.selectKeshiBykid(kid);
		//根据id查找出医生
		Doctor doctor = doctorService.selectDoctorByTid(did);
		//创建历史就诊记录
		Historicalpatients historicalpatients = new Historicalpatients(1, doctor, keshi, persons, new Date(), 0);
		historicalpatientsService.addNewHistoricalpatients(historicalpatients );
		//创建新的总账单
		Totalpay totalpay = new Totalpay(null, persons, keshi.getKprice(), 0, null);
		totalService.insertTotalPay(totalpay );
		//保证用户只有一个未支付的账单，此时新建的账单就是未支付的账单
		totalpay=totalService.selectTotalPaynoPayBypid(persons.getPid());
		System.out.println(totalpay);
		//添加账单明细
		String expenses = "挂号，科室："+keshi.getKname()+"医生:"+doctor.getTname();
		Paytype paytype = new Paytype(1, "挂号收费");
		Paydetail paydetail = new Paydetail(null, totalpay, keshi.getKprice(), paytype , expenses);
		paydetailService.insertPaydetail(paydetail );
		String msg = "患者:"+persons.getPname()+"挂号成功，请输入下一位挂号患者信息";
		model.addAttribute("msg", msg);
		return "selectpersons";
	}
	@Transactional
	@RequestMapping("/register/oldpersonsRegister")
	public String oldPersonsRegister(Model model,Persons persons,Integer did,Integer kid) {
		//更新患者信息
		personsService.updatePersonsByPid(persons);
		//根据id查找出科室
		Keshi keshi = keshiService.selectKeshiBykid(kid);
		//根据id查找出医生
		Doctor doctor = doctorService.selectDoctorByTid(did);
		//创建历史就诊记录
		Historicalpatients historicalpatients = new Historicalpatients(1, doctor, keshi, persons, new Date(), 0);
		historicalpatientsService.addNewHistoricalpatients(historicalpatients );
		//创建新的总账单
		Totalpay totalpay = new Totalpay(null, persons, keshi.getKprice(), 0, null);
		totalService.insertTotalPay(totalpay );
		//保证用户只有一个未支付的账单，此时新建的账单就是未支付的账单
		totalpay=totalService.selectTotalPaynoPayBypid(persons.getPid());
		System.out.println(totalpay);
		//添加账单明细
		String expenses = "挂号，科室："+keshi.getKname()+"医生:"+doctor.getTname();
		Paytype paytype = new Paytype(1, "挂号收费");
		Paydetail paydetail = new Paydetail(null, totalpay, keshi.getKprice(), paytype , expenses);
		paydetailService.insertPaydetail(paydetail );
		String msg = "患者:"+persons.getPname()+"挂号成功，请输入下一位挂号患者信息";
		model.addAttribute("msg", msg);
		return "selectpersons";
	}
}
