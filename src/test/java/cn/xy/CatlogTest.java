package cn.xy;

import cn.xy.novelwebproject.utils.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.net.URLDecoder;

public class CatlogTest {
		@Test
		public void InsertCatlog(){
				//1、读取文件列表
				String root = "E:\\BookFile";
				File rootFile = new File(root);
				File[] fileList = null;//小说目录
				if(rootFile.isDirectory()){
						fileList = rootFile.listFiles();
				}
				//2、获取文件
				BufferedReader br = null;
				//2.2、遍历小说的章节
				for(File novel:fileList){
						String novelname = novel.getName().replace(".txt","");
						File[] catlogs = novel.listFiles();

						//3、读取内容
						try {
								for(File f:catlogs){
										String catlogname = f.getName().replace(".txt","");//章节名
										System.out.println(catlogname);
										br = new BufferedReader(new FileReader(f));
										String res="";
										String txt = "";

										while((res=br.readLine())!=null){
												//System.out.println(res);
												txt += res;
										}

										//4、将内容写入数据库
										boolean flag = JDBCUtils.InsertCatlog(txt,catlogname,novelname);
										if (!flag)break;
								}
						}catch (Exception e){
								e.printStackTrace();
						}finally {
								try {
										br.close();
								} catch (IOException e) {
										e.printStackTrace();
								}
						}
				}

		}
		@Test
		public void test02() throws UnsupportedEncodingException {
				String fileName ="src\\main\\webapp\\static\\txt"+ File.separator + "全职法师";
				System.out.println(new File("src\\main\\webapp\\static\\txt").exists());
				String decode = URLDecoder.decode("%E5%86%99%E5%86%99%E5%B0%8F%E8%AF%B4%E5%B0%B1%E6%97%A0%E6%95%8C%E4%BA%86","utf-8");
				System.out.println(decode);
		}
		@Test
		public void test03(){
				String titile = "第二章空门-";
				System.out.println(titile.contains("第一章"));
		}
}
