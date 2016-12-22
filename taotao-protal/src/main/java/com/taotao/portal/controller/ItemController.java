package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		ItemInfo item = itemService.getItemBaseById(itemId);
		model.addAttribute("item", item);
		return "item";
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId){
		return itemService.getItemDescById(itemId);
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId){
		return itemService.getItemParamById(itemId);
	}

}
