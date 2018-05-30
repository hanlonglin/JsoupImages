package util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadUtil {

	// �ɹ�����1 ʧ�ܷ��ش�����Ϣ
	public String downloadImg(String path, String fname, String imgUrl) {
		String msg = "";
		URL url;
		try {
			File file = new File(path);
			if (!file.exists())
				if (!file.mkdir())
					return path + "�ļ��д���ʧ��";
			url = new URL(imgUrl);
			// ������������
			DataInputStream dis = new DataInputStream(url.openStream());
			String newImageName = path + "/" + fname;
			// ����һ���µ��ļ�
			FileOutputStream fos = new FileOutputStream(new File(newImageName));
			byte[] buffer = new byte[1024];
			int length;
			// ��ʼ�������
			while ((length = dis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			dis.close();
			fos.close();
			msg = "1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = e.getMessage().toString();
		}
		return msg;
	}

	public String downloadImg2(String path, String filename, String imgUrl) {
		String msg = "";
		try {
			// ����URL
			URL url = new URL(imgUrl);
			// ������
			URLConnection con = url.openConnection();
			con.addRequestProperty("referrer", "no-referrer");
			con.setRequestProperty("Referer", imgUrl);
			// ��������ʱΪ5s
			con.setConnectTimeout(5 * 1000);
			// ������
			InputStream is = con.getInputStream();

			// 1K�����ݻ���
			byte[] bs = new byte[1024];
			// ��ȡ�������ݳ���
			int len;
			// ������ļ���
			File sf = new File(path);
			if (!sf.exists()) {
				if (!sf.mkdirs())
					return path + "�ļ��д���ʧ��";
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
			// ��ʼ��ȡ
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// ��ϣ��ر���������
			os.close();
			is.close();
			msg = "1";
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage().toString();
		}
		return msg;
	}
}
