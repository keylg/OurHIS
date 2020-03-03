package com.woniuxy.springboot.HIS.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Medicinejson {
	private String code;
	private String msg;
	private Integer count;
	private List<Medicine> data;
	
}
