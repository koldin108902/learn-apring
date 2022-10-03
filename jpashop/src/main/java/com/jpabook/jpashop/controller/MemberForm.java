package com.jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
