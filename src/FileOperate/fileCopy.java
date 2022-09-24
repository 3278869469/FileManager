package FileOperate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import FileOpen.fileOpen;

public class fileCopy {
	private static String sourse;
	private static String target;
	
	public static boolean Copy(String Name) {
		sourse = fileOpen.getCurrPath() + File.separator + Name;
		target = fileOpen.getCurrPath() + File.separator + "copy_" + Name;
		File file = new File(sourse);
		if(file.isFile()) {
			try {
				return FileCopy(sourse, target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		else {
			try {
				newfile.newFolder(target);
				Clone(sourse);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	}
	
	/* 复制单个文件
	 * filePath 文件的路径； copyPath 文件要复制到的路径
	 */
	public static boolean FileCopy(String filePath, String copyPath) throws IOException {
		File src = new File(filePath);
		File dest = new File(copyPath);
		if (src.isFile() && src.exists()) {
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);
			byte b[] = new byte[1024 * 1024 * 5];
			int len = in.read(b);
			while (len != -1) {
				out.write(b, 0, len);
				len = in.read(b);
			}
			in.close();
			out.close();
			return true;
		} else {
			return false;
		}
	}
		
		public static void Clone(String url){
			//获取目录下所有文件
			File f = new File(url);
			File[] allf = f.listFiles();
			for(File fi:allf) {
				try {
					String URL = target+fi.getAbsolutePath().substring(sourse.length());
					System.out.println(URL);
					if(fi.isDirectory()) {
						Createflies(URL);
					}else {
						fileInputOutput(fi.getAbsolutePath(),URL);
					}
					if(fi.isDirectory()) {
						Clone(fi.getAbsolutePath());
					}
				}catch (Exception e) {
					System.out.println("error");
				}
			}
		}
		
		public static void fileInputOutput(String sourse,String target) {
			try {
				File s = new File(sourse);
				File t = new File(target);
				FileInputStream fin = new FileInputStream(s);
				FileOutputStream fout = new FileOutputStream(t);
				byte[] a = new byte[1024*1024*4];	
				int b = -1;
				while((b = fin.read(a))!=-1) {
					fout.write(a,0,b);
				}
				fout.close();
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public static boolean Createflies(String name) {
			boolean flag=false;
			File file=new File(name);
			if(file.mkdir() == true){
				flag=true;
			}else {
				flag=false;
			}
			return flag;
		}
}
