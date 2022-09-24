package FileOpen;

import java.io.File;
import java.util.Stack;

public class fileOpen {
	    // 默认文件路径
		private static File file = new File("D:\\");
		static Stack<String> filePath = new Stack<String>();

		public static File getFile() {
			return file;
		}

		//
		public static String getCurrPath() {
			return file.getPath();
		}

		//
		public static String getParentPath() {
			if (filePath.isEmpty()) {
				return null;
			} else {
				// 返回顶层元素，不出栈
				return filePath.firstElement();
			}
		}

		public static String getfileName() {
			return file.getName();
		}

		public static File FileOpen(String fileName) {
			// 当前文件的路径压栈
			String str = file.getPath() + File.separator + fileName;
			File f = new File(str);
			//选中的文件 是文件夹才进入，压栈
			if (f.isDirectory()) {
				filePath.push(file.getPath());
				file = new File(str);
			}
			return file;
		}
		
		public static void fileParent()
		{
			if(filePath.isEmpty())
				return;
			else
				file = new File(filePath.pop());
		}
}
