package com.tianmao.controller;

import com.tianmao.pojo.*;
import com.tianmao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 胡建德
 */
@RestController
public class ForeRESTController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private PropertyValueService propertyValueService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/forehome")
    public List<Category> home(){
        List<Category> categories = categoryService.list();
        productService.fill(categories);
        productService.fillByRow(categories);
        return categories;
    }

    @PostMapping("/foreregister")
    public Result register(@RequestBody User user){
        String name = user.getName();
        name = HtmlUtils.htmlEscape(name);
        if(userService.isExist(name)){
            return Result.fail("用户名，已存在，清重新输入");
        }
        user.setName(name);
        userService.add(user);
        return Result.success();
    }

    @PostMapping("/forelogin")
    public Result login(@RequestBody User user, HttpSession session){
        User getuser = userService.get(user.getName(),user.getPassword());
        if(getuser == null){
            return Result.fail("用户不存在");
        }
        session.setAttribute("user",user);
        return Result.success();
    }

    @GetMapping("foreproduct/{id}")
    public Result getProduct(@PathVariable("id") int id){
        Product product = productService.get(id);
        product.setProductSingleImages(productImageService.getSingleImage(product));
        product.setProductDetailImages(productImageService.getDetailImages(product));
        List<PropertyValue> pvs = propertyValueService.list(product.getId());
        List<Review> reviews = reviewService.list(product);
        productService.setSaleAndReviewNumber(product);
        productImageService.setFirstProdutImage(product);
        Map<String,Object> map= new HashMap<>();
        map.put("product", product);
        map.put("pvs", pvs);
        map.put("reviews", reviews);
        return Result.success(map);
    }

}
