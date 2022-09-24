package FileOperate;

import java.io.File;

import FileOpen.fileOpen;

public class FileDele {

	public static boolean Delete(String Name) {
		String fileName = fileOpen.getCurrPath() + File.separator + Name;
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	//删除单个文件
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		//再次判断 是不是文件，文件是不是存在
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} else {
			//删除失败
			return false;
		}
	}

	// 删除目录（文件夹）以及目录下的文件
	public static boolean deleteDirectory(String dir) {
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			return false;
		}
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
}
