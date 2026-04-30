package ${pkg}.exception;

import ${pkg}.${globalVo};
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 捕获自定义异常
    @ExceptionHandler(GlobalException.class)
    public GlobalVo${r'<'}?${r'>'} handleGlobalException(GlobalException e) {
        return GlobalVo.fail(e.getMessage());
    }

    // 可选：捕获其他未处理异常
    @ExceptionHandler(Exception.class)
    public GlobalVo${r'<'}?${r'>'} handleOtherException(Exception e) {
        return GlobalVo.fail(e.getMessage());
    }
}