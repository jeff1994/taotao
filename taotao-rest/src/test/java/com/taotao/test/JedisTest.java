package com.taotao.test;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void testJedisSingle(){
		//创建一个jedis对象
		Jedis jedis = new Jedis("192.168.72.101", 6379);
		jedis.set("key1", "TEST jEDIS");
		System.out.println(jedis.get("key1"));
		//关闭jedis
		jedis.close();
	}
	
	/**
	 * 使用连接池
	 */
	@Test
	public void testJedisPool() {
		//创建jedis连接池
		JedisPool pool = new JedisPool("192.168.72.101", 6379);
		//从连接池中获得Jedis对象
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		//关闭jedis对象
		jedis.close();
		pool.close();
	}
	
	/**
	 * 集群版测试
	 * @Title: testJedisCluster 
	 */
	@Test
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.72.101", 7001));
		nodes.add(new HostAndPort("192.168.72.101", 7002));
		nodes.add(new HostAndPort("192.168.72.101", 7003));
		nodes.add(new HostAndPort("192.168.72.101", 7004));
		nodes.add(new HostAndPort("192.168.72.101", 7005));
		nodes.add(new HostAndPort("192.168.72.101", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes);
		
		cluster.set("key1", "1000");
		String string = cluster.get("key1");
		System.out.println(string);
		
		cluster.close();
	}
	
	
	/**
	 * 单机版测试
	 * <p>Title: testSpringJedisSingle</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}

	/**
	 * 集群版测试
	 * @Title: testSpringJedisCluster 
	 * @Description: TODO
	 * @param 
	 * @return void
	 * @throws
	 */
	@Test
	public void testSpringJedisCluster() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster =  (JedisCluster) applicationContext.getBean("redisCluster");
		String string = jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close();
	}


}
