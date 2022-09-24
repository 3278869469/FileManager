package FileOperate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import FileOpen.fileOpen;


public class FileUnzip{

	private static final int BUFFER_SIZE = 1024*5;

	public static void Un(File srcFile, String destDirPath) throws RuntimeException {
		     // 判断源文件是否存在
		     if (!srcFile.exists()) {
		         throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
		     }
		     // 开始解压
		     ZipFile zipFile = null;
		     try {
		         zipFile = new ZipFile(srcFile);
		         Enumeration<?> entries = zipFile.entries();
		         while (entries.hasMoreElements()) {
		             ZipEntry entry = (ZipEntry) entries.nextElement();
		             if (entry.isDirectory()) {
		                 String dirPath = destDirPath + "/" + entry.getName();
		                 File dir = new File(dirPath);
		                 dir.mkdirs();
		             } else {
		                 File targetFile = new File(destDirPath + "/" + entry.getName());
		                 if(!targetFile.getParentFile().exists()){
		                     targetFile.getParentFile().mkdirs();
		                 }
		                 targetFile.createNewFile();
		                 InputStream is = zipFile.getInputStream(entry);
		                 FileOutputStream fos = new FileOutputStream(targetFile);
		                 int len;
		                 byte[] buf = new byte[BUFFER_SIZE];
		                 while ((len = is.read(buf)) != -1) {
		                     fos.write(buf, 0, len);
		                 }
		                 fos.close();
		                 is.close();
		             }
		         }
		     } catch (Exception e) {
		         throw new RuntimeException("unzip error from ZipUtils", e);
		     } finally {
		         if(zipFile != null){
		             try {
		                 zipFile.close();
		             } catch (IOException e) {
		                 e.printStackTrace();
		             }
		         }
		     }
	}
	
	public static void Unzip(String fileName) {
		if(fileName.substring(fileName.length()-4, fileName.length()).equals(".rar") || fileName.substring(fileName.length()-4, fileName.length()).equals(".zip")) {
			String f = fileOpen.getCurrPath() + File.separator + fileName;
			File file = new File(f);
			String destDirPath = fileOpen.getCurrPath() + File.separator + "Unzip_file";
			newfile.newFolder(destDirPath);
			Un(file, destDirPath);
		}
	}
}
