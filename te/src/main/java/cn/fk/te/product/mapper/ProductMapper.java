package cn.fk.te.product.mapper;


import cn.fk.common.dao.BaseCRUDDao;
import cn.fk.te.product.model.po.Product;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductMapper extends BaseCRUDDao<Long, Product> {
}