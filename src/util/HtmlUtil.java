package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtil {

	DownloadUtil downUtil;
	IniUtil iniUtil;

	public HtmlUtil() {
		downUtil = new DownloadUtil();
		iniUtil = new IniUtil();
	}

	public void parseHtml(String url, JTextArea jtx) {
		jtx.append("\n");
		jtx.append("��ʼ��ȡͼƬ......\n");
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			System.out.println("html��ȡ��" + doc.toString().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ȡhtmlʧ�ܣ�" + e.getMessage().toString());
		}
		Long timeStrap = System.currentTimeMillis();
		Elements elements = doc.select("img");
		System.out.println("��ȡͼƬ������" + elements.size());
		for (Element element : elements) {
			String src = element.attr("src");
			System.out.println("src:" + src);
			String imgName = src.substring(src.lastIndexOf("/") + 1);
			System.out.println("imgname:" + imgName + ",path:" + iniUtil.getPath());
			if (src.startsWith("http")) {
				String msg = downUtil.downloadImg2(iniUtil.getPath() + "/" + timeStrap, imgName, src);
				if (msg.equals("1")) {
					System.out.println("src:" + src + "���سɹ�");
					jtx.append(src + "���سɹ�\n");
				} else {
					System.out.println("src:" + src + "����ʧ��");
					jtx.append(src + "����ʧ��," + msg + "\n");
				}
			} else {
				System.out.println("������Ч��ַ");
				jtx.append(src + "������Ч��ַ\n");
			}
		}
		jtx.append("���سɹ���ͼƬ���浽" + iniUtil.getPath() + "/" + timeStrap);

	}

	public void getHtml(String url) {
		String cookie = getCookies(url);
		try {
			Connection conn = Jsoup.connect(url);

			// conn.header("Host","xueqiu.com");
			// conn.header("Connection","keep-alive");
			// conn.header("Cache-Control","max-age=0");
			// conn.header("Upgrade-Insecure-Requests","1");
			// conn.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64)
			// AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
			// conn.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			// conn.header("Accept-Encoding","gzip, deflate, sdch, br");
			// conn.header("Accept-Language","zh-CN,zh;q=0.8");

			conn.header("Cookie", cookie);
			conn.method(Method.GET);
			conn.followRedirects(true);
			conn.ignoreContentType(true);
			Response response = conn.execute();
			String body = response.body();
			System.out.println("body:" + body);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	private String getCookies(String url) {
		try {
			Connection conn = Jsoup.connect(url);
			conn.data("username", "hanlonglin123").data("password", "longlin1234");
			conn.method(Method.GET);
			conn.followRedirects(true);
			Response response;
			response = conn.execute();
			Map<String, String> getCookies = response.cookies();
			String Cookie = getCookies.toString();
			Cookie = Cookie.substring(Cookie.indexOf("{") + 1, Cookie.lastIndexOf("}"));
			Cookie = Cookie.replaceAll(",", ";");
			System.out.println("cookies:" + Cookie);
			return Cookie;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public String getHtml2(String url) {
		String cookie=getCookies(url);
		String result = "";// ���صĽ��
		BufferedReader in = null; // ��ȡ��Ӧ��
		StringBuffer sb = new StringBuffer(); // �洢����
		try {
			System.out.println("url:" + url);

			// ����URL����
			java.net.URL connURL = new java.net.URL(url);
			// ��URL����
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
			// ����ͨ������
			httpConn.setRequestProperty("Accept", "/*");
			httpConn.setRequestProperty("Cookie", cookie);
			// httpConn.setRequestProperty("Connection","Keep-Alive");
			// httpConn.setRequestProperty("","");
			// ����ʵ�ʵ�����
			httpConn.connect();
			// ��ȡ��Ӧͷ
			Map<String, List<String>> headers = httpConn.getHeaderFields();
			// ������Ӧͷ�ֶ�
			for (String key : headers.keySet()) {
				System.out.println("key:" + key + ",content:" + headers.get(key));
			}
			// ����BufferedReader ����������ȡURL����Ӧ�������ñ��뷽ʽ
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "GBK"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
				System.out.println("line:" + line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException eo) {
				eo.printStackTrace();
			}
		}

		return result;
	}
	
	private String getCookies2(String url)
	{
		String result="";
		BufferedReader in = null; // ��ȡ��Ӧ��
		StringBuffer sb = new StringBuffer(); // �洢����
		try {
			System.out.println("url:" + url);

			// ����URL����
			java.net.URL connURL = new java.net.URL(url);
			// ��URL����
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
			// ����ͨ������
			httpConn.setRequestProperty("Accept", "/*");
			// httpConn.setRequestProperty("Connection","Keep-Alive");
			// httpConn.setRequestProperty("","");
			// ����ʵ�ʵ�����
			httpConn.connect();
			// ��ȡ��Ӧͷ
			Map<String, List<String>> headers = httpConn.getHeaderFields();
			// ������Ӧͷ�ֶ�
			for (String key : headers.keySet()) {
				System.out.println("key:" + key + ",content:" + headers.get(key));
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
