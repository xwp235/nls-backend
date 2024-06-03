package com.nlsapi.core.business.enums;

import lombok.Getter;

@Getter
public enum FileTransStatusEnum {

    INIT("I", "初始"),
    SUBTITLE_INIT("SI", "待生成字幕"),
    SUBTITLE_PENDING("SP", "生成字幕中"),
    SUBTITLE_SUCCESS("SS", "生成字幕成功"),
    SUBTITLE_FAILURE("SF", "生成字幕失败"),
    DELETED("D", "失效");

    private final String code;

    private final String desc;

    FileTransStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FileTransStatusEnum getByCode(String code){
        for(var e: FileTransStatusEnum.values()){
            if(code.equals(e.getCode())){
                return e;
            }
        }
        return  null;
    }

}
