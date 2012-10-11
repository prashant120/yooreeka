package org.yooreeka.examples.search;

import java.io.File;
import java.io.IOException;

import org.yooreeka.algos.search.lucene.LuceneIndexBuilder;
import org.yooreeka.util.internet.crawling.core.CrawlData;
import org.yooreeka.util.internet.crawling.core.CrawlDataProcessor;
import org.yooreeka.util.internet.crawling.util.FileUtils;

public class LuceneIndexer {
	
	private String baseDir;
	
	private String luceneIndexDir;
	
	public LuceneIndexer(String dir) {
		
		baseDir = dir;
		luceneIndexDir = baseDir+System.getProperty("file.separator")+"lucene-index";
	}
	
    public void run() {
    
        // load existing data
        CrawlData crawlData = new CrawlData(baseDir);
        crawlData.init(); 
        
        File luceneIndexRootDir = new File(getLuceneDir());
        
        // Delete the index directory, if it exists
        FileUtils.deleteDir(luceneIndexRootDir);
        luceneIndexRootDir.mkdirs();
        
        CrawlDataProcessor luceneIndexBuilder = null;
		try {
			luceneIndexBuilder = new LuceneIndexBuilder(luceneIndexRootDir, crawlData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.print("Starting the indexing ... ");
        
        luceneIndexBuilder.run();

        System.out.println("Indexing completed! \n");        
    }
    
    public String getLuceneDir() {
    	
    	return luceneIndexDir;
    }
    
}
