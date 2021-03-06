package com.taotao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIResult queryContentList(Long categoryId, Integer page, Integer rows){
		return contentService.queryContentListByCatId(categoryId, page, rows);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveContent(TbContent content){
		return contentService.insertContent(content);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContent(String ids){
		return contentService.deleteContentByCatId(ids);
	}
	
}
