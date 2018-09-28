package com.fan.ckexchange.test;

import com.fan.ckexchange.ClassLoaderDriver;
import com.fan.ckexchange.classloader.ZookeeperClassLoader;
import com.fan.ckexchange.outdata.SetDataToZkUtils;
import com.fan.ckexchange.resource.ShareResource;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @description zk
 */
public class ZkClassLoaderTest {
	private String zkServer="vdata1:2181,vdata2:2181,vdata3:2181";
	private String nodePath="/vdata";
	
	@Test
	public void test() throws Exception{
		boolean initFlag=ClassLoaderDriver.init(zkServer, nodePath);
		System.out.println("------init------"+initFlag);
		
		ZookeeperClassLoader classLoader=null;
		while(true){
			if(ShareResource.isUpdateFlag()){
				classLoader=new ZookeeperClassLoader();
				ShareResource.setUpdateFlag(false);
			}
			
			Class claz=classLoader.loadClass("com.fan.test.Vdata");
			Object obj=claz.newInstance();
			Method method=claz.getMethod("print");
			method.invoke(obj);
			System.out.println("----------over---------------");
			Thread.sleep(8000);
		}
	}
	
	@Test
	public void setDataTest(){
		SetDataToZkUtils.setDataToZk("com", zkServer, nodePath);
		System.out.println("--------over loader-------------");
	}
}