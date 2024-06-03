package com.nlsapi.core.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileTransLangEnum {

    LANG0("ZO0RG58O86t8BkSa", "普通话"),
    LANG1("S4i4dPIkswYrcsdJ", "英语"),
    LANG2("h5l1MjQGctbISvko", "中英混合"),
    LANG3("BC1cMypzLD372hHO", "日语");

    private final String code;

    private final String desc;

}
