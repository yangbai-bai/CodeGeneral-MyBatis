package ${pkg}.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yangbaibai.${pkg}.entity.enums.ResponseEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 通用响应VO
* @author ${author}
* @since ${date}
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalVo${r'<'}T${r'>'} {
    private Integer code;
    private T data;
    private String msg;


    /**
    * 适用于返回数据和消息的情况
    * @param data 数据
    * @param msg 消息
    * @return 通用Vo
    * @param ${r'<'}T${r'>'} 返回的数据类型
    */
    public static ${r'<'}T${r'>'} GlobalVo${r'<'}T${r'>'} success(T data, String msg) {
        return new GlobalVo<>(ResponseEnums.SUCCEED.getCode(), data, msg);
    }

    /**
    * 适用于操作成功但是不返回数据的情况
    * @param msg 返回的消息
    * @return 通用Vo
    * @param ${r'<'}T${r'>'} 返回的数据类型
    */
    public static ${r'<'}T${r'>'} GlobalVo${r'<'}T${r'>'} success(String msg) {
        return new GlobalVo<>(ResponseEnums.SUCCEED.getCode(), null, msg);
    }

    /**
    * 适用于获取数据但是不返回消息的情况
    * @param data 数据
    * @return 通用Vo
    * @param ${r'<'}T${r'>'} 返回的数据类型
    */
    public static ${r'<'}T${r'>'} GlobalVo${r'<'}T${r'>'} success(T data) {
        return new GlobalVo<>(ResponseEnums.SUCCEED.getCode(), data, null);
    }

    /**
    * 适用于内部发生错误但是返回消息的情况
    * @param msg 消息
    * @return 通用Vo
    * @param ${r'<'}T${r'>'} 返回的数据类型
    */
    public static ${r'<'}T${r'>'} GlobalVo${r'<'}T${r'>'} fail(String msg) {
        return new GlobalVo<>(ResponseEnums.FAILED.getCode(), null, msg);
    }

    /**
    * 适用于权限不足的情况
    * @param msg 消息
    * @return 通用Vo
    * @param ${r'<'}T${r'>'} 返回的数据类型
    */
    public static ${r'<'}T${r'>'} GlobalVo${r'<'}T${r'>'} permission(String msg) {
        return new GlobalVo<>(ResponseEnums.PERMISSION.getCode(), null, msg);
    }

    /**
    * 适用于权限不足但是返回数据的情况
    * @param data 数据
    * @param msg 消息
    * @return 通用Vo
    * @param ${r'<'}T${r'>'} 返回的数据类型
    */
    public static ${r'<'}T${r'>'} GlobalVo${r'<'}T${r'>'} permission(T data, String msg) {
        return new GlobalVo<>(ResponseEnums.PERMISSION.getCode(), data, msg);
    }
}
