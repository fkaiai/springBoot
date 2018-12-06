package cn.fk.te.product.controller;

import cn.fk.common.model.vo.Page;
import cn.fk.common.util.StringUtil;
import cn.fk.te.product.model.po.Product;
import cn.fk.te.product.model.po.ProductExtend;
import cn.fk.te.product.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Api(value = "产品列表", tags = "product")
@Slf4j
public class ProductController {

    //Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductServiceImpl productService;



    @GetMapping(value = "/getPageList")
    @ApiOperation(value = "按条件获取产品列表",notes = "分页参数(pageNum,pageSize)")
    @ApiImplicitParams({@ApiImplicitParam(name="params",value="筛选条件",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="loanMonthMin",value="最小月份",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="loanMonthMax",value="最大月份",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="minPosition",value="最小额度",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="maxPosition",value="最大额度",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="pageNum",value="第几页",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="pageSize",value="每页几条",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="sort",value="升降序",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="order",value="按什么字段排序",dataType="string", paramType = "query")})
    public Page getPageList(String params,
                        String loanMonthMin,
                        String loanMonthMax,
                        String minPosition,
                        String maxPosition,
                        Integer pageNum,
                        Integer pageSize,
                        String sort,
                        String order){

        ProductExtend productExtend= new ProductExtend();

        if(StringUtil.isNotEmpty(params)){
            String [] paramsArr= params.split(",");
            for(int i=0;i<paramsArr.length;i++){
                if(paramsArr[i].equals("shortTime")) productExtend.setShortTime(1);
                if(paramsArr[i].equals("zhima")) productExtend.setZhima(1);
                if(paramsArr[i].equals("creditCard")) productExtend.setCreditCard(1);
                if(paramsArr[i].equals("large")) productExtend.setLarge(1);
                if(paramsArr[i].equals("repaymentCreditCard")) productExtend.setRepaymentCreditCard(1);
                if(paramsArr[i].equals("salary")) productExtend.setSalary(1);
                if(paramsArr[i].equals("mortgage")) productExtend.setMortgage(1);
                if(paramsArr[i].equals("passing")) productExtend.setPassing(1);
                if(paramsArr[i].equals("creditTest")) productExtend.setCreditTest(1);
                if(paramsArr[i].equals("youLike")) productExtend.setYouLike(1);
                if(paramsArr[i].equals("newLoan")) productExtend.setNewLoan(1);
                if(paramsArr[i].equals("todayAdopt")) productExtend.setTodayAdopt(1);
            }
        }
        if(StringUtil.isNotEmpty(loanMonthMin) && StringUtil.isEmpty(loanMonthMax)) loanMonthMax="1000";
        if(StringUtil.isNotEmpty(minPosition) && StringUtil.isEmpty(maxPosition)) maxPosition="10000000";
        if(StringUtil.isNotEmpty(loanMonthMin)) productExtend.setLoanMonthMin(Integer.valueOf(loanMonthMin));
        if(StringUtil.isNotEmpty(loanMonthMax)) productExtend.setLoanMonthMax(Integer.valueOf(loanMonthMax));

        if(StringUtil.isNotEmpty(order)){
            if(order.equals("orders")) productExtend.setIsEnable("1");
            if(order.equals("today_orders")) productExtend.setIsEnableToday("1");
            if(order.equals("hot_orders")) productExtend.setIsEnableHot("1");
        }

        Page<Long,Product> list=  productService.getPageList(productExtend,pageNum,pageSize,sort,order);
        return list;
    }


public static void main(String[] args) {
        System.out.println(11);
    }

}
