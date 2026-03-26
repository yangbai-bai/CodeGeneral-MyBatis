package com.yangbaibai.${pkg}.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnums {
    SUCCEED(0),
    FAILED(-1),
    PERMISSION(1);

    private final Integer code;
}
