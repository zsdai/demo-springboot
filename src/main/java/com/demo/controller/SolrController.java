package com.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("solr")
public class SolrController {

	@Autowired
	private SolrClient solrClient;

	@RequestMapping("query/{data}")
	public String query(@PathVariable String data, Map<String, Object> root) throws Exception {
		// 查询solr
		SolrQuery solrQuery = new SolrQuery("title:" + data);
		QueryResponse response = this.solrClient.query(solrQuery);
		SolrDocumentList results = response.getResults();
		
		// 解析查询结果
		List<String> list = new ArrayList<String>();
		for (SolrDocument solrDocument : results) {
			list.add(solrDocument.get("title").toString());
		}

		// 传递数据
		root.put("total", results.getNumFound());
		root.put("list", list);

		return "solr";

	}

	@RequestMapping("save/{id}/{title}")
	@ResponseBody
	public String save(@PathVariable String id, @PathVariable String title) throws Exception {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", id);
		doc.addField("title", title);

		this.solrClient.add(doc);
		this.solrClient.commit();

		return "保存成功";

	}
}
