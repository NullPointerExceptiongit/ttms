package cn.tedu.ttms.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/product")
public class ProductController {
	 /**��Ʒ�б�ҳ��*/
	 @RequestMapping("/listUI")
	 public String listUI(){
		 return "product/product_list";
	 }
}
