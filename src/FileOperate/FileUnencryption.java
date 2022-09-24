package FileOperate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import FileOpen.fileOpen;

public class FileUnencryption {

	// 解密
	public static void unencryption(String fileName) throws IOException {
		//先判断是不是加密文件
		if (fileName.subSequence(0, 11).equals("encryption_")) {
			System.out.println("解密开始");
			byte pwd = 123;
			String Path = fileOpen.getCurrPath() + File.separator + fileName;
			String UnencryptPath = fileOpen.getCurrPath() + File.separator + "unencryption_" + fileName;
			FileInputStream f = new FileInputStream(Path);
			FileOutputStream fout = new FileOutputStream(UnencryptPath);
			int n = f.available() / 5;
			byte buf[] = new byte[n];
			buf = new byte[n];
			int count = 0;
			while ((count = f.read(buf, 0, n)) != -1) {
				for (int i = 0; i < count; i++) {
					buf[i] = (byte) (buf[i] ^ pwd);
				}
				fout.write(buf, 0, count);
			}
			f.close();
			fout.close();
		}
	}
}
