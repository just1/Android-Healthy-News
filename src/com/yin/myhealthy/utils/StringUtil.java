package com.yin.myhealthy.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtil {
	// ɾ������
	public static String DelEmptyLine(String str) {

		// �Ȱ�stringתΪinputstream��Ȼ������BufferedReader��readline���в���
		InputStream is = new ByteArrayInputStream(str.getBytes());

		// InputStream is=null;
		BufferedReader br = null;
		String tmp;
		String res = null;
		int i = 0;
		try {
			// is = new BufferedInputStream(new FileInputStream(file));
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			while ((tmp = br.readLine()) != null) {
				if (tmp.equals("")) {
					;
				}

				else {
					if (res == null) {
						res = tmp + "\n";
					} else {
						res += tmp + "\n";
					}

					i++;
					// System.out.println(i);
				}
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}
}
