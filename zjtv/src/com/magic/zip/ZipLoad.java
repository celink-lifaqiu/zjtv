package com.magic.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipLoad {
	static Logger logger = LoggerFactory.getLogger(ZipLoad.class);
	
	public static void zipFiles(File[] srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		ZipOutputStream out;
		try {
			// ZipOutputStream类：完成文件或文件夹的压缩
			out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in;
				try{
					in = new FileInputStream(srcfile[i]);
				} catch(FileNotFoundException e){
					logger.info(e.getMessage());
					continue;
				}
				if(in != null){
					out.putNextEntry(new ZipEntry(srcfile[i].getName()));
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					out.closeEntry();
					in.close();
				}
			}
			out.close();
			logger.info("Zip Completed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
