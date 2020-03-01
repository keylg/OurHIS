package com.woniuxy.springboot.HIS.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
	 private Integer mid;

	    private String mname;

	    private Double mprice;

	    private String msize;

	    private String munit;

	    private String manufacturer;
}
