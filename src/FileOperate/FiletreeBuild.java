package FileOperate;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import FileOpen.fileOpen;

public class FiletreeBuild {
	// 文件夹的进入，树的创建
	public static MutableTreeNode buildTree(String parentName) {
		File file = fileOpen.FileOpen(parentName);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(file.getName());
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.getName());
				root.add(childNode);
				if (child.isDirectory()) {
					DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode();
					childNode.add(childNode2);
				}
			}
		}
		return root;
	}

	// 文件树的刷新
	public static MutableTreeNode buildTree() {
		File file = fileOpen.getFile();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(file.getName());
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.getName());
				root.add(childNode);
				if (child.isDirectory()) {
					DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode();
					childNode.add(childNode2);
				}
			}
		}
		return root;
	}

}
