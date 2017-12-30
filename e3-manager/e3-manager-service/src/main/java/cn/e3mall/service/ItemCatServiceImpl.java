package cn.e3mall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.pojo.TreeNode;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired 
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<TreeNode> getItemCatList(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
//		设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
//		将list转换成treeNode列表
		List<TreeNode> lNodes = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			TreeNode treeNode = new TreeNode();
			Long id = tbItemCat.getId();
			treeNode.setId(id);
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			lNodes.add(treeNode);
		}
		return lNodes;
	}
	

	
	

}
