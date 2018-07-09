package util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class GUI {

	private Shell shell;
	public GUI(Shell sShell) {
		
		shell = new Shell(sShell, SWT.CLOSE);
		
		shell.setSize(350, 250);
		shell.setText("ListSample");
		
		String itemLeft[] = new String[20];				// 定义保存左侧列表框中的数据
		String itemright[] = new String[0];				// 定义保存右侧列表框中的数据
		
		for(int i = 0; i < itemLeft.length; i++)
			itemLeft[i] = "item" + i;					// 初始化左侧字符串数组
		
		final List left = new List(shell, SWT.MULTI|SWT.V_SCROLL);
														// 定义左侧列表框，可选择多个且带有滚动条
		left.setBounds(10, 10, 100, 180);				// 设置位置和大小
		left.setItems(itemLeft);						// 设置选项数据
		left.setToolTipText("Choosed List");			// 设置提示
		
		final List right = new List(shell, SWT.MULTI|SWT.V_SCROLL);
														// 定义右侧列表框，可选择多个且带有滚动条
		right.setBounds(170, 10, 100, 180);
		right.setItems(itemright);
		right.setToolTipText("Choosed List");
		
		// 创建事件监听类，为内部类
		SelectionAdapter listener = new SelectionAdapter() {
														// 按钮单击事件处理的方法
			public void widgetSelected(SelectionEvent e) {
														// 取得触发事件的空间对象（按钮）
				Button bt = (Button)e.widget;
				if(bt.getText().equals(">"))			// 如果是“>”按钮
					verifyValue(left.getSelection(), left, right);
				else if(bt.getText().equals(">>"))		// 如果是“>>”按钮
					verifyValue(left.getItems(), left, right);
				else if(bt.getText().equals("<"))		// 如果是“<”按钮
					verifyValue(right.getSelection(), right, left);
				else if(bt.getText().equals("<<"))		// 如果是“<<”按钮
					verifyValue(right.getItems(), right, left);
				else if(bt.getText().equals("Up"))		// 如果是“Up”按钮
				{
					int index = right.getSelectionIndex();
														// 获得当前选中选项的索引值
					if(index <= 0)						// 如未选中，则返回
						return ;
					String currentValue = right.getItem(index);
														// 如果选中了选项值，获得当前选项的值
					right.setItem(index, right.getItem(index - 1));							
					right.setItem(index - 1, currentValue);
														// 将选中的选项与上一个选项交换值
					right.setSelection(index - 1);		// 设定上一选项为选中状态
				}
				else if(bt.getText().equals("Down"))	// 如果是“Down”按钮
				{
														// 与“Up”按钮逻辑相同
					int index = right.getSelectionIndex();
					if(index >= right.getItemCount() - 1)
						return ;
					String currentValue = right.getItem(index);
					right.setItem(index, right.getItem(index + 1));
					right.setItem(index + 1, currentValue);
					right.setSelection(index + 1);
				}
			}
		};
		
	// 定义按钮
		// 定义（单）右移按钮
		Button bt1 = new Button(shell, SWT.NONE);
		bt1.setText(">");
		bt1.setBounds(130, 20, 25, 20);
		bt1.addSelectionListener(listener);				// 为按钮注册事件，其他的按钮类似
		// 定义（多）右移按钮
		Button bt2 = new Button(shell, SWT.NONE);
		bt2.setText(">>");
		bt2.setBounds(130, 55, 25, 20);
		bt2.addSelectionListener(listener);
		// 定义（多）左移按钮
		Button bt3 = new Button(shell, SWT.NONE);
		bt3.setText("<<");
		bt3.setBounds(130, 90, 25, 20);
		bt3.addSelectionListener(listener);
		// 定义（单）左移按钮
		Button bt4 = new Button(shell, SWT.NONE);
		bt4.setText("<");
		bt4.setBounds(130, 125, 25, 20);
		bt4.addSelectionListener(listener);
		// 定义上移按钮
		Button bt5 = new Button(shell, SWT.NONE);
		bt5.setText("Up");
		bt5.setBounds(285, 70, 40, 20);
		bt5.addSelectionListener(listener);
		// 定义下移按钮
		Button bt6 = new Button(shell, SWT.NONE);
		bt6.setText("Down");
		bt6.setBounds(285, 105, 40, 20);
		bt6.addSelectionListener(listener);
		shell.open();
	}

	private void verifyValue(String[] selected, List from, List to) {
		
		for(int i = 0; i < selected.length; i++)
		{											
			from.remove(selected[i]);					// 从一个列表中移除该选项值													
			to.add(selected[i]);						// 添加到另一列表中
		}
		
	}
	public static void main(String[]args){
		GUI gui=new GUI(new Shell(SWT.Activate));
	}
}