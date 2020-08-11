package com.tianmao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianmao.mapper.ProductImageMapper;
import com.tianmao.pojo.ProductImage;
import com.tianmao.service.ProductImageService;
import com.tianmao.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 胡建德
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductImageServiceImpl implements ProductImageService {

    @Resource
    private ProductImageMapper productImageMapper;

    @Resource
    private ProductService productService;



    @Override
    public List<ProductImage> list(String type, int pid) {
        QueryWrapper<ProductImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",pid);
        queryWrapper.eq("type",type);
        return productImageMapper.selectList(queryWrapper);
    }

    @Override
    public void add(ProductImage productImage) {
        productImageMapper.add(productImage);
        productImage.setId(productImageMapper.getId());
    }

    @Override
    public ProductImage get(String id) {
        return productImageMapper.selectById(id);
    }

    @Override
    public void delete(String id) {
        productImageMapper.deleteById(id);
    }


}
