package umc.study.web.dto;

import lombok.Getter;
import umc.study.validator.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDto {

    @Getter
    public static class JoinDto {
        String name;
        Integer gender;
        Integer birthYear;
        Integer birthMonth;
        Integer birthDay;
        String address;
        String specAddress;

        @ExistCategories
        List<Long> preferCategory;
    }

}
