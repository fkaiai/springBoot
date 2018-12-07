package cn.fk.te.product.service.impl;


import cn.fk.common.service.AbstractBaseCRUDService;
import cn.fk.te.product.mapper.ProductMapper;
import cn.fk.te.product.model.po.Product;
import cn.fk.te.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gyl
 */
@Service
public class ProductServiceImpl extends AbstractBaseCRUDService<Integer, Product, ProductMapper> implements ProductService {

    @Autowired
    ProductMapper productMapper;

}
