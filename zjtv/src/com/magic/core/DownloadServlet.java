package com.magic.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DownloadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4858238146187786023L;

	protected static Log log = LogFactory.getLog(DownloadServlet.class);

	private String rootPath = "";
//	private String rootPath2 = "";
	private String contentType = "application/x-msdownload";
	private String enc = "utf-8";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
//		rootPath = PropertyUtil.getProperty("org.jfeng.file.root");
//		if(rootPath.startsWith("c:")){
//			rootPath2 = rootPath.replace("c:", "d:");
//		}else if(rootPath.startsWith("d:")){
//			rootPath2 = rootPath.replace("d:", "c:");
//		}
//		if (rootPath.charAt(rootPath.length() - 1) != '/') {
//			rootPath += '/';
//		}
//		if (rootPath2.charAt(rootPath2.length() - 1) != '/') {
//			rootPath2 += '/';
//		}
		String tempStr = config.getInitParameter("contentType");
		if (tempStr != null && !tempStr.equals("")) {
			contentType = tempStr;
		}
		tempStr = config.getInitParameter("enc");
		if (tempStr != null && !tempStr.equals("")) {
			enc = tempStr;
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
//		String fileName = URLEncoder.encode(uri
//				.substring(uri.lastIndexOf("/") + 1), enc);
		
		String fileName = URLDecoder.decode(uri
				.substring(uri.lastIndexOf("/") + 1), enc);
		
		fileName = fileName.replace("%2520"," ");
		String path = uri.substring(uri.lastIndexOf("/download") + 10);
		path =  URLDecoder.decode(path, enc);
		String fullFilePath = rootPath + path;
//		String fullFilePath2 = rootPath2 + path;
		fullFilePath = fullFilePath.replaceAll("%20","_");
//		fullFilePath2 = fullFilePath2.replaceAll("%20","_");
		log.debug("####Download file path : " + fullFilePath);

		File file = new File("/"+fullFilePath);
//		if(!file.exists()) file = new File(fullFilePath2);
		if (file.exists()) {
			response.reset();
			response.setContentType(contentType);
			fileName = URLEncoder.encode(fileName, enc);
			response.addHeader("Content-Disposition",
					"attachment; filename=\"" + fileName + "\"");
			int fileLength = (int) file.length();
			response.setContentLength(fileLength);
			if (fileLength != 0) {
				InputStream inStream = null;
				ServletOutputStream servletOS = null;
				try {
					inStream = new FileInputStream(file);
					byte[] buf = new byte[4096];
					servletOS = response.getOutputStream();
					int readLength;
					while (((readLength = inStream.read(buf)) != -1)) {
						servletOS.write(buf, 0, readLength);
					}
					servletOS.flush();
				} catch (Exception e) {
					log.error(e);
				} finally {
					if (inStream != null)
						inStream.close();
					if (servletOS != null)
						servletOS.close();
				}
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
