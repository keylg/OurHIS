package com.woniuxy.springboot.HIS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacykc {
    private Integer yfkcid;

    private Medicine medicine;

    private Integer yfkcnum;

   
}