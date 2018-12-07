package cn.fk.te.product.model.po;


import cn.fk.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class Product implements BaseModel<Integer> {
    //1
    //2
    //5

    private Integer id;

    private String name;

    private String logo;

    private Integer minPosition;

    private Integer maxPosition;

    private String loanTimeLimit;

    private String labelTxt;

    private String labelUnder;

    private Date addTime;

    private String rate;

    private String extensionUrl;

    private String remark;

    private String customerPhone;

    private String isJump;

    private String isEnable;

    private String orders;

    private String winType;

    private String floatStatus;

    private String price;
    private String payMethod;
    private String manageUrl;

    private String ext1;
    private String ext2;
    private String isEnableToday;
    private String todayOrders;
    private String isEnableHot;
    private String hotOrders;
    private String details;

    /*private String sort;//按什么排序
    private String sort2;//次排序
    private String showAll;//是否展示全部*/


}