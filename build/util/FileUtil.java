package com.zhjy.qzfwgz.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhjy.common.util.Base64;

import java.util.Calendar;

/**
 * 文件操作类
 * 
 * @author xwtt
 * 
 */
public class FileUtil {

	private final static Log log = LogFactory
			.getLog("com.zhjy.jjgx.util.FileUtil");

	/**
	 * 下载文件路径
	 */
	public String BAK_DIRS = "c:\\jjgx_file\\bak";

	/**
	 * 生成目录
	 * 
	 * @param path
	 *            目录地址
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public boolean mkDirs(String path) throws IllegalArgumentException {
		if (CommonTools.isNull(path)) {
			log.error("不能生成文件,文件目录为空");
			throw new IllegalArgumentException("生成文件目录名不能为空");
		}
		boolean isMk = false;

		File file = new File(path);
		if (!file.exists()) {
			isMk = file.mkdirs();
			if (isMk) {
				if (log.isDebugEnabled())
					log.debug("生成" + path + "成功!!!");
			} else {
				log.error("生成" + path + "失败!!!");
			}
		}
		return isMk;
	}

	/**
	 * 保存文件
	 * 
	 * @param streamIn
	 *            输入流
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public void save(byte[] content, String filePath, String fileName)
			throws IOException, IllegalArgumentException {
		if (CommonTools.isNull(filePath) || CommonTools.isNull(fileName)) {
			log.error("保存文件路径及文件不能为空; \n fileName === " + fileName
					+ "\n  filePath===" + filePath);
			throw new IllegalArgumentException("保存文件路径及文件不能为空");
		}
		if (content == null) {
			log.error("文件流不能为空");
			throw new IOException("文件流不能为空");
		}
		InputStream is = new ByteArrayInputStream(content);
		this.save(is, filePath, fileName);
	}

	public void save(InputStream streamIn, String filePath, String fileName)
			throws IOException, IllegalArgumentException {
		if (CommonTools.isNull(filePath) || CommonTools.isNull(fileName)) {
			log.error("保存文件路径及文件不能为空; \n fileName === " + fileName
					+ "\n  filePath===" + filePath);
			throw new IllegalArgumentException("保存文件路径及文件不能为空");
		}
		if (streamIn == null) {
			log.error("文件流不能为空");
			throw new IOException("文件流不能为空");
		}
		try {
			this.mkDirs(filePath);
			OutputStream streamOut = new FileOutputStream(filePath + fileName);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
				streamOut.write(buffer, 0, bytesRead);
			}
			streamOut.close();
			streamIn.close();
		} catch (IOException ex) {
			throw new IOException("保存文件失败");
		}
	}

	public FileInputStream load(String filePath, String fileName)
			throws IOException {
		FileInputStream fileStream = null;
		if (CommonTools.isNull(filePath) || CommonTools.isNull(fileName)) {
			log.error("读取文件路径及文件不能为空; \n fileName === " + fileName
					+ "\n  filePath===" + filePath);
			throw new IllegalArgumentException("读取文件路径及文件不能为空");
		}
		try {
			fileStream = new FileInputStream(filePath + fileName);
		} catch (IOException ex) {
			throw new IOException("读取文件失败");
		}
		return fileStream;
	}

	/**
	 * 移动文件到所指定的目录
	 * 
	 * @param srcFile
	 * @param desPath
	 * @throws IOException
	 */
	public void moveFile(String srcFilePath, String srcFileName, String desPath)
			throws IOException {
		FileInputStream fileStream = null;
		try {
			fileStream = this.load(srcFilePath, srcFileName);
			this.save(fileStream, desPath, srcFileName);
		} catch (IOException e) {
			throw new IOException("读取文件失败");
		}

	}

	public void deleteFile(String filePath, String fileName) {
		File file = new File(filePath + fileName);
		file.delete();

	}

	public static File[] getFileAllList(String filePath) {
		File file = new File(filePath);
		return file.listFiles();
	}

	public static List getFileTenDayList(String filePath) {
		List fileList = new ArrayList();
		File[] allFiles = null;
		File file = new File(filePath);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -184);

		if (allFiles != null) {
			for (int i = 0; i < allFiles.length; i++) {
				File fileBuffer = allFiles[i];
				if (fileBuffer.lastModified() > c.getTime().getTime()) {
					fileList.add(fileBuffer);
				}

			}
		}

		return fileList;
	}

	public static String parse(String filePath) {

		return filePath.replaceAll("\\\\", "/");
	}

	public static String readUrlToBy(String url) {
		// String url =
		// "http://61.49.38.1/wssp/admin/affair/upload/20060316_U4doPHFEBw.doc";
		URL fileur = null;
		String fileline = null;
		byte[] b = null;
		try {
			fileur = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("Can&acute;t get URL: ");
		}
		InputStream filecon;
		try {
			filecon = fileur.openStream();
			b = FileUtil.inputStreamToByte(filecon);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		String s = Base64.encodeBytes(b);
		// System.out.println(s);
		// String path="E:/testfile/";
		// String fileName="20060316_U4doPHFEBw.doc";
		// JxtxServiceTest.writeToBytes(Base64.decode(s),path+fileName);

		// FileUtil.save(filecon, path, fileName);
		return s;
	}

	public static void writeToBytes(byte bytes[], String fileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName, true);
			fos.write(bytes);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				fos.close();
			} catch (IOException iex) {
			}
		}
	}

	public static byte[] inputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}

	public byte[] getFileToBytes(String filePath, String fileName)
			throws IOException {
		FileInputStream fis = load(filePath, fileName);
		return FileUtil.inputStreamToByte(fis);
	}

	public static String createFileName(String index) {
		
		return DateUtil.getNotFlagToday() + "_" + new Date().getTime() + "_"
				+ index;

	}
}
