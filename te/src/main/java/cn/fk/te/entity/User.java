package cn.fk.te.entity;

import cn.fk.te.vo.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class User implements BaseModel<Long> {

    private Long id;

    @ApiModelProperty(value = "姓名", example = "张三")
    @NotBlank(message = "用户名不能")
    private String username;

    private Date birthday;

    @ApiModelProperty(value = "性别", example = "1")
    private String sex;

    @ApiModelProperty(value = "地址", example = "广东省")
    @NotBlank(message = "地址不能")
    private String address;

}