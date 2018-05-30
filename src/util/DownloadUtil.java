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

	// 成功返回1 失败返回错误信息
	public String downloadImg(String path, String fname, String imgUrl) {
		String msg = "";
		URL url;
		try {
			File file = new File(path);
			if (!file.exists())
				if (!file.mkdir())
					return path + "文件夹创建失败";
			url = new URL(imgUrl);
			// 打开网络输入流
			DataInputStream dis = new DataInputStream(url.openStream());
			String newImageName = path + "/" + fname;
			// 建立一个新的文件
			FileOutputStream fos = new FileOutputStream(new File(newImageName));
			byte[] buffer = new byte[1024];
			int length;
			// 开始填充数据
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
			// 构造URL
			URL url = new URL(imgUrl);
			// 打开连接
			URLConnection con = url.openConnection();
			con.addRequestProperty("referrer", "no-referrer");
			con.setRequestProperty("Referer", imgUrl);
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(path);
			if (!sf.exists()) {
				if (!sf.mkdirs())
					return path + "文件夹创建失败";
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
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
