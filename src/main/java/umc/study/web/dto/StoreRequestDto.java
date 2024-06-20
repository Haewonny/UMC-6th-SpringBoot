package umc.study.web.dto;


import lombok.Getter;

public class StoreRequestDto {
    @Getter
    public static class AddDto {
        String name;
        String address;
    }
}
