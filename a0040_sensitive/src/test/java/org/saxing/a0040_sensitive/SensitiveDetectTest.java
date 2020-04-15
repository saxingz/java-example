package org.saxing.a0040_sensitive;

import junit.framework.TestCase;
import org.saxing.a0040_sensitive.entity.AuditLevel;
import org.saxing.a0040_sensitive.entity.AuditTextDetailDTO;
import org.saxing.a0040_sensitive.entity.AuditWay;
import org.saxing.a0040_sensitive.entity.SensitiveType;
import org.saxing.a0040_sensitive.util.sensi.SensitiveDetect;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SensitiveDetectTest extends TestCase{
	
	public void test() throws Exception{
		
		// 使用默认单例（加载默认词典）
		SensitiveDetect filter = SensitiveDetect.DEFAULT;
		// 向过滤器增加一个词
		filter.put("婚礼上唱春天在哪里");
		
		// 待过滤的句子
		String sentence = "然后，市长在婚礼上唱春天在哪里。";
		// 进行过滤
//		String filted = detect.detect(sentence, '*');
		List<AuditTextDetailDTO> filter1 = filter.detect(sentence, AuditLevel.NORMAL.getCode(), SensitiveType.NORMAL.getCode(), AuditWay.SELF_WORD.getCode());
		System.out.println(filter1);

//		// 如果未过滤，则返回输入的String引用
//		if(sentence != filted){
//			// 句子中有敏感词
//			System.out.println(filted);
//		}
		
	}
	
	public void testLogic(){
		
		SensitiveDetect filter = new SensitiveDetect();
		
		filter.put("你好");
		filter.put("你好1");
		filter.put("你好2");
		filter.put("你好3");
		filter.put("你好4");

		List<AuditTextDetailDTO> filter1 = filter.detect("你好天不见", AuditLevel.NORMAL.getCode(), SensitiveType.NORMAL.getCode(), AuditWay.SELF_WORD.getCode());
		System.out.println(filter1);
		
	}
	
	public void testSpeed() throws Exception{
		
		File dir = new File("E:\\xiaoshuo");
		
		List<String> testSuit = new ArrayList<String>(1048576);
		long length = 0;
		
		for(File file: dir.listFiles()){
			if(file.isFile() && file.getName().endsWith(".txt")){
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb18030"));
				for(String line = br.readLine(); line != null; line = br.readLine()){
					if(line.trim().length() > 0){
						testSuit.add(line);
						length += line.length();
					}
				}
				br.close();
			}
		}
		
		System.out.println(String.format("待过滤文本共 %d 行，%d 字符。", testSuit.size(), length));
		
		
		SensitiveDetect filter = SensitiveDetect.DEFAULT;
		List<AuditTextDetailDTO> allResult = new ArrayList<>();


		long timer = System.currentTimeMillis();
		for(String line: testSuit){
			List<AuditTextDetailDTO> detect = filter.detect(line, AuditLevel.NORMAL.getCode(), SensitiveType.NORMAL.getCode(), AuditWay.SELF_WORD.getCode());
			allResult.addAll(detect);
		}
		timer = System.currentTimeMillis() - timer;
		System.out.println(String.format("过滤耗时 %1.3f 秒， 速度为 %1.1f字符/毫秒", timer * 1E-3, length / (double) timer));
		System.out.println("总检测结果数： " + allResult.size());
		System.out.println("有问题的结果数 " + allResult.stream().filter(audit -> audit.getFeature().length() > 1).count());
	}

}
