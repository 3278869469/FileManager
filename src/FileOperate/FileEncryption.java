package FileOperate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import FileOpen.fileOpen;

public class FileEncryption {

	// 加密
	public static void encryption(String fileName) throws IOException {
		//类似于密钥
		byte pwd = 123;
		String Path = fileOpen.getCurrPath() + File.separator + fileName;
		String EncryptPath = fileOpen.getCurrPath() + File.separator + "encryption_" + fileName;
		File file = new File(Path);
		if (file.isFile()) {
			FileInputStream f = new FileInputStream(Path);
			FileOutputStream fout = new FileOutputStream(EncryptPath);
			int n = f.available() / 5;
			byte buf[] = new byte[n];
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
		else {
			return;
		}
	}

}
