package cn.fk.te.product.model.po;

import cn.fk.common.model.BaseModel;
import lombok.Data;

@Data
public class ProductExtend extends Product{
    //1

    //短期小额贷
    private Integer shortTime;
    //芝麻分贷款
    private Integer zhima;
    //信用卡贷款
    private Integer creditCard;
    //大额贷
    private Integer large;
    //代还信用卡
    private Integer repaymentCreditCard;
    //工薪族贷
    private Integer salary;
    //房/车抵押贷
    private Integer mortgage;
    //高通过率
    private Integer passing;

    private Integer loanMonthMin;

    private Integer loanMonthMax;
    //信用评测
    private Integer creditTest;
    //猜你喜欢
    private Integer youLike;
    //贷款新品
    private Integer newLoan;
    //今日必过
    private Integer todayAdopt;


}