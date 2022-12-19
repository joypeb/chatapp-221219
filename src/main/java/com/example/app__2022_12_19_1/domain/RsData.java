package com.example.app__2022_12_19_1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RsData<P> {
    private String resultCode;
    private String msg;
    private P data;
}
