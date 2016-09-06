package com.newssite.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.newssite.dao.ArticleDao;
import com.newssite.model.Article;

/**
 * 
 * The HitResolver class provides methods to check and store
 * a product's views(hits) outside of accessing the database
 * or modifying the cache on each product select.
 * <p>It also updates the database on a set interval
 *
 */
@Component
@Scope("singleton")
public class HitResolver /*implements DisposableBean*/{
	
	
	private Map<String,Object[]> productHits;
	private ArticleDao productDao;
	private ScheduledExecutorService executor;
	
	@Autowired
	public HitResolver(ArticleDao dao){
		productDao = dao;
		productHits = new ConcurrentHashMap<>();
		this.executor = Executors.newScheduledThreadPool(4);
		executor.scheduleAtFixedRate(new UpdateDBRunnable(), 30, 30, TimeUnit.SECONDS);
	}
	/**
	 * Sets a given product's views from in memory storage.
	 * <p>Updates the storage and sets a flag to update record.</p>
	 * @param p the product to check
	 */
	public void resolve(Article p){
		if(productHits.containsKey(p.getHeadline())){
			p.setHits(((long) productHits.get(p.getHeadline())[0])+1);
		}else{
			p.setHits(p.getHits() + 1);
		}		
		executor.execute(new UpdateMapRunnable(p));
	}
	
	@PreDestroy
	public void cleanUp(){
		System.out.println("Shutting down hit resolver");
		shutdownAndAwaitTermination();
	}
	
	private void shutdownAndAwaitTermination() {
			
		executor.shutdown(); // Disable new tasks from being submitted
		try{
		    // Wait a while for existing tasks to terminate
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
			   executor.shutdownNow(); // Cancel currently executing tasks
		       // Wait a while for tasks to respond to being cancelled
		       if (!executor.awaitTermination(60, TimeUnit.SECONDS))
		           System.err.println("Pool did not terminate");
		     }
		   } catch (InterruptedException ie) {
		     // (Re-)Cancel if current thread also interrupted
		     executor.shutdownNow();
		     // Preserve interrupt status
		     Thread.currentThread().interrupt();
		   }
	}
	
	private class UpdateDBRunnable implements Runnable{
		
		@Override
		public void run() {
			productDao.updateHits(productHits.entrySet());
		}
		
	}

	private class UpdateMapRunnable implements Runnable {
		
		private Article a;
		
		protected UpdateMapRunnable(Article p){
			this.a = p;
		}

		@Override
		public void run() {
			synchronized (productHits) {
				if(productHits.containsKey(a.getHeadline())){
					Object [] pi = productHits.get(a.getHeadline());
					pi[0] = a.getHits();
					pi[1] = true;
				}else{
					productHits.put(a.getHeadline(), new Object[]{a.getHits(),true});
				}
			}
		}
	}

}
