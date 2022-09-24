package FileOperate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import FileOpen.fileOpen;


public class FileZip {

	/**
	 * path   要压缩的文件路径
	 * format  生成的格式
	 */
	public static void generateFile(String path, String format) throws Exception {

		File file = new File(path);
		if (!file.exists()) {
			throw new Exception("路径 " + path + " 不存在文件，无法进行压缩...");
		}
		// 用于存放压缩文件的文件夹
		String generateFile = file.getParent() + File.separator + "CompressFile";
		File compress = new File(generateFile);
		if (!compress.exists()) {
			compress.mkdirs();
		}
		
		String generateFileName = compress.getAbsolutePath() + File.separator + file.getName() + "." + format;
		FileOutputStream outputStream = new FileOutputStream(generateFileName);
		ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
		generateFile(zipOutputStream, file, "");
		zipOutputStream.close();
	}

	private static void generateFile(ZipOutputStream out, File file, String dir) throws Exception {

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			out.putNextEntry(new ZipEntry(dir + "/"));
			dir = dir.length() == 0 ? "" : dir + "/";
			for (int i = 0; i < files.length; i++) {
				generateFile(out, files[i], dir + files[i].getName());
			}
		} else { 
			FileInputStream inputStream = new FileInputStream(file);
			out.putNextEntry(new ZipEntry(dir));
			int len = 0;
			byte[] bytes = new byte[1024];
			while ((len = inputStream.read(bytes)) > 0) {
				out.write(bytes, 0, len);
			}
			inputStream.close();
		}
	}

	public static void zip(String fileName) {
		String path = fileOpen.getCurrPath() + File.separator + fileName;
		String format = "rar";
		try {
			generateFile(path, format);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
