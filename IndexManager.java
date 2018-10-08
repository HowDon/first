package com.itheima.lucene;

public class IndexManager {
	
	
	private IndexWriter indexWriter;
	@Before
	public void init() throws Exception {
		indexWriter = new IndexWriter(FSDirectory.open(new File("D:/temp/index")),
				new IndexWriterConfig(Version.LATEST, new IKAnalyzer()));
	}

	
	
	
	@Test
	
	public void addDocument() throws Exception {
		
		
		
		// 1）使用IndexWriter打开索引库
		// 2）创建一个Document对象
		
		
		Document document = new Document();
		
		
		// 3）向Document中添加field
		Field name = new TextField("name", "新添加的文档新添加的文档新添加的文档新添加的文档新添加的文档新添加的文档新添加的文档新添加的文档新添加的文档新添加的文档新添加的文档新添加的文档aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa apache", Store.YES);
		name.setBoost(10f);
		document.add( name);
		
		document.add(new TextField("name1", "新添加的文档1", Store.YES));
		document.add(new TextField("name2", "新添加的文档2", Store.YES));
		// 4）把文档对象添加到索引库
		indexWriter.addDocument(document);
		// 5）提交
		indexWriter.commit();
		// 6）关闭IndexWriter对象
		indexWriter.close();
	}
	
	@Test
	public void deleteAllDocument() throws Exception {
		//删除全部文档
		indexWriter.deleteAll();
		//提交修改
		indexWriter.commit();
		indexWriter.close();
	}
	
	@Test
	public void deleteDocumentByQuery() throws Exception {
		//参数就是查询条件
		Query query = new TermQuery(new Term("name", "apache"));
		indexWriter.deleteDocuments(query);
		//提交修改
		indexWriter.commit();
		indexWriter.close();
		
	}
	
	@Test
	public void updateDocument() throws Exception {
		//创建一个新的Document
		Document doc = new Document();
		doc.add(new TextField("name", "更新后的文档1", Store.YES));
		doc.add(new TextField("content", "更新后的文档1内容", Store.YES));
		doc.add(new TextField("name1", "更新后的文档1", Store.YES));
		indexWriter.updateDocument(new Term("name", "spring"), doc);
		//提交修改
		indexWriter.commit();
		indexWriter.close();
		
	}
}
