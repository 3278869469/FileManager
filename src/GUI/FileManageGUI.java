package GUI;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import FileOpen.fileOpen;
import javax.swing.JTree;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import FileOperate.*;

public class FileManageGUI {

	private JFrame frame;
	private DefaultTreeModel model = new DefaultTreeModel(FiletreeBuild.buildTree());
	private JTree tree = new JTree(model);
	private JScrollPane scrollPane = new JScrollPane(tree);

	private JPopupMenu msgInfoPopMenu = null;
	private JMenuItem copyItem = new JMenuItem("拷贝");
	private JMenuItem deleteItem = new JMenuItem("删除");
	private JMenuItem encryItem = new JMenuItem("加密");
	private JMenuItem UnencryItem = new JMenuItem("解密");
	private JMenuItem zipItem = new JMenuItem("压缩");
	private JMenuItem UnzipItem = new JMenuItem("解压");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileManageGUI window = new FileManageGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileManageGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. 
	  *   初始化
	 */
	private void initialize() {
		// treeModel.addTreeModelListener(this);
		frame = new JFrame();
		frame.setBounds(100, 100, 474, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tree.addMouseListener(new JTreeListener());

		JButton btnNewButton = new JButton("新建文件夹");
		btnNewButton.addActionListener(new ButtonListener());

		JButton btnNewButton_1 = new JButton("...(上级目录)");
		btnNewButton_1.addActionListener(new ButtonListener());

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addGap(86)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(108, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton,
												GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		scrollPane.setColumnHeaderView(btnNewButton_1);

		frame.getContentPane().setLayout(groupLayout);
	}

	
	/* 用内部类的形式实现 监听器
	*    比较符号面向对象思想
	*    并且可以直接访问 GUI的组件。
	*/
	public class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("新建文件夹")) {
				newfile.newFolder();
				model.setRoot(FiletreeBuild.buildTree());
			} else if (e.getActionCommand().equals("...(上级目录)")) {
				fileOpen.fileParent();
				model.setRoot(FiletreeBuild.buildTree());
			}
		}
	}
	
	

	public class JTreeListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
			// 右键
			if (e.getSource() == tree && e.isMetaDown()) {
				if (selPath != null)// 谨防空指针异常!双击空白处是会这样
				{
					// 懒汉式
					// 这样处理是防止msgInfoPopMenu加入多次Item
					if (msgInfoPopMenu == null) {
						msgInfoPopMenu = new JPopupMenu();
					} else {
						msgInfoPopMenu.removeAll();
					}

					msgInfoPopMenu.add(copyItem);
					msgInfoPopMenu.add(deleteItem);
					// 分割线
					msgInfoPopMenu.addSeparator();
					msgInfoPopMenu.add(encryItem);
					msgInfoPopMenu.add(UnencryItem);
					// 分割线
					msgInfoPopMenu.addSeparator();
					msgInfoPopMenu.add(zipItem);
					msgInfoPopMenu.add(UnzipItem);

					// 显示的位置
					msgInfoPopMenu.show(e.getComponent(), e.getX(), e.getY());
					copyItem.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
							String chooseName = node.toString();
							fileCopy.Copy(chooseName);
							model.setRoot(FiletreeBuild.buildTree());
						}
					});
					deleteItem.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							DefaultMutableTreeNode node =  (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
							String chooseName = node.toString();
							FileDele.Delete(chooseName);
							model.setRoot(FiletreeBuild.buildTree());
						}
					});
					encryItem.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							try {
								DefaultMutableTreeNode node =  (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
								String chooseName = node.toString();
								FileEncryption.encryption(chooseName);
								model.setRoot(FiletreeBuild.buildTree());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					UnencryItem.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							try {
								DefaultMutableTreeNode node =  (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
								String chooseName = node.toString();
								FileUnencryption.unencryption(chooseName);
								model.setRoot(FiletreeBuild.buildTree());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					zipItem.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							try {
								DefaultMutableTreeNode node =  (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
								String chooseName = node.toString();
								FileZip.zip(chooseName);
								model.setRoot(FiletreeBuild.buildTree());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					UnzipItem.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							try {
								DefaultMutableTreeNode node =  (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
								String chooseName = node.toString();
								FileUnzip.Unzip(chooseName);
								model.setRoot(FiletreeBuild.buildTree());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			// 如果在这棵树上点击了2次,即双击
			TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
			if (e.getSource() == tree && e.getClickCount() == 2) {
				// 按照鼠标点击的坐标点获取路径
				if (selPath != null) {
					// 获取双击的地方
					DefaultMutableTreeNode node =  (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
					String chooseName = node.toString();
					model.setRoot(FiletreeBuild.buildTree(chooseName));
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
