package cn.fk.te.product.controller;

import cn.fk.common.model.vo.DataBox;
import cn.fk.te.product.model.Constant;
import cn.fk.te.product.model.po.ProductExtend;
import cn.fk.te.product.service.impl.ProductExtendServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productExtend")
@Api(value = "产品列表扩展", tags = "产品列表扩展")
@Slf4j
public class ProductExtendController {

    @Autowired
    ProductExtendServiceImpl productExtendService;

    @GetMapping(value = "/get")
    @ApiOperation(value = "按条件获取产品列表",notes = "")
    @ApiImplicitParams({@ApiImplicitParam(name="id",value="id",dataType="string", paramType = "query")})
    public DataBox get(String id){
        return DataBox.newInstance(Constant.SUCCESS,Constant.MSG_SUCCESS,productExtendService.get(Integer.valueOf(id)));
    }

    @PostMapping(value = "/insertOrUpdate")
    @ApiOperation(value = "新增或覆盖",notes = "")
    public DataBox insertOrUpdate(@RequestBody ProductExtend productExtend){

        if(productExtend==null) return DataBox.newInstance(Constant.FAIL,Constant.MSG_FIAL);
        return DataBox.newInstance(Constant.SUCCESS,Constant.MSG_SUCCESS,productExtendService.insertOrUpdate(productExtend));

    }
}
