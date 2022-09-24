package FileOperate;

import java.io.File;

import FileOpen.fileOpen;

public class newfile {
	// 文件夹的新建
		public static String newFolder() {
			for (int i = 1;; i++) {
				String str;
				if (i == 1) {
					str = fileOpen.getCurrPath() + "\\newFile";
				} else {
					str = fileOpen.getCurrPath() + "\\newFile" + Integer.toString(i);
				}
				File tempfile = new File(str);
				if (!tempfile.exists()) { // 如果文件夹不存在
					tempfile.mkdir(); // 创建新文件夹
					// 返回文件夹的名字
					if (i == 1) {
						return "newFile";
					} else {
						return "newFile" + Integer.toString(i);
					}
				}
			}
		}
		
		public static void newFolder(String Path) {
				File tempfile = new File(Path);
				if (!tempfile.exists()) { // 如果文件夹不存在
					tempfile.mkdir(); // 创建新文件夹
				}
		}

}
