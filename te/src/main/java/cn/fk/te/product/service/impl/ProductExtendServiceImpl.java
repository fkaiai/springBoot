package cn.fk.te.product.service.impl;


import cn.fk.common.service.AbstractBaseCRUDService;
import cn.fk.te.product.mapper.ProductExtendMapper;
import cn.fk.te.product.mapper.ProductMapper;
import cn.fk.te.product.model.po.ProductExtend;
import cn.fk.te.product.service.ProductExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gyl
 */
@Service
public class ProductExtendServiceImpl extends AbstractBaseCRUDService<Integer, ProductExtend, ProductExtendMapper> implements ProductExtendService {
    @Autowired
    ProductExtendMapper productExtendMapper;
}
