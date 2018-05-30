package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import util.HtmlUtil;

public class MainUI extends JFrame implements ActionListener{
	private JButton jb_start,jb_clear;
	private JPanel jp1;
	private JTextArea jtx_url;
	
	//������
	HtmlUtil htmlUtil=new HtmlUtil();

	public MainUI() {
		jb_start = new JButton("��ʼ��ȡ");
		jb_clear=new JButton("���");
		jp1=new JPanel(new BorderLayout());
		jp1.add(jb_start,BorderLayout.EAST);
		jp1.add(jb_clear, BorderLayout.WEST);
		
		jtx_url = new JTextArea();
		jtx_url.setSize(400, 50);
		jtx_url.setLineWrap(true); // �����Զ����й���
		jtx_url.setWrapStyleWord(true); // ������в����ֹ���

		this.setLayout(new BorderLayout());
		this.add(jp1, BorderLayout.SOUTH);
		this.add(new JScrollPane(jtx_url), BorderLayout.CENTER);
		this.setTitle("ͼƬ��ȡ����");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		jb_start.addActionListener(this);
		jb_clear.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource()==jb_clear)
		{
			jtx_url.setText("");
		}
		if(event.getSource()==jb_start)
		{
			String url=jtx_url.getText().trim();
			if(!url.startsWith("http"))
			{
				//��ʾ����Ϊ��
				JOptionPane.showMessageDialog(this, "��ַ��Ч"); 
			}else {
				//��ʼ��ȡͼƬ
				//htmlUtil.parseHtml(url,jtx_url);
			    htmlUtil.getHtml2(url);
			}
			
		}
	}

}
