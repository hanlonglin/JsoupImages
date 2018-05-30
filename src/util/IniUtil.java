package util;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;

public class IniUtil {
	String path = "";
	public IniUtil()
	{
		readConfig();
	}

	public int readConfig() {
		try {
			Ini ini = new Ini(new File(System.getProperty("user.dir") + "/jsoupHtml.ini")); // System.getProperty("user.dir")获取当前路径
			Section section = ini.get("config");
			path = section.get("path");
		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	public String getPath() {
		return path;
	}
}
