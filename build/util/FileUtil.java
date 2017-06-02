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
 * �ļ�������
 * 
 * @author xwtt
 * 
 */
public class FileUtil {

	private final static Log log = LogFactory
			.getLog("com.zhjy.jjgx.util.FileUtil");

	/**
	 * �����ļ�·��
	 */
	public String BAK_DIRS = "c:\\jjgx_file\\bak";

	/**
	 * ����Ŀ¼
	 * 
	 * @param path
	 *            Ŀ¼��ַ
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public boolean mkDirs(String path) throws IllegalArgumentException {
		if (CommonTools.isNull(path)) {
			log.error("���������ļ�,�ļ�Ŀ¼Ϊ��");
			throw new IllegalArgumentException("�����ļ�Ŀ¼������Ϊ��");
		}
		boolean isMk = false;

		File file = new File(path);
		if (!file.exists()) {
			isMk = file.mkdirs();
			if (isMk) {
				if (log.isDebugEnabled())
					log.debug("����" + path + "�ɹ�!!!");
			} else {
				log.error("����" + path + "ʧ��!!!");
			}
		}
		return isMk;
	}

	/**
	 * �����ļ�
	 * 
	 * @param streamIn
	 *            ������
	 * @param filePath
	 *            �ļ�·��
	 * @param fileName
	 *            �ļ���
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public void save(byte[] content, String filePath, String fileName)
			throws IOException, IllegalArgumentException {
		if (CommonTools.isNull(filePath) || CommonTools.isNull(fileName)) {
			log.error("�����ļ�·�����ļ�����Ϊ��; \n fileName === " + fileName
					+ "\n  filePath===" + filePath);
			throw new IllegalArgumentException("�����ļ�·�����ļ�����Ϊ��");
		}
		if (content == null) {
			log.error("�ļ�������Ϊ��");
			throw new IOException("�ļ�������Ϊ��");
		}
		InputStream is = new ByteArrayInputStream(content);
		this.save(is, filePath, fileName);
	}

	public void save(InputStream streamIn, String filePath, String fileName)
			throws IOException, IllegalArgumentException {
		if (CommonTools.isNull(filePath) || CommonTools.isNull(fileName)) {
			log.error("�����ļ�·�����ļ�����Ϊ��; \n fileName === " + fileName
					+ "\n  filePath===" + filePath);
			throw new IllegalArgumentException("�����ļ�·�����ļ�����Ϊ��");
		}
		if (streamIn == null) {
			log.error("�ļ�������Ϊ��");
			throw new IOException("�ļ�������Ϊ��");
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
			throw new IOException("�����ļ�ʧ��");
		}
	}

	public FileInputStream load(String filePath, String fileName)
			throws IOException {
		FileInputStream fileStream = null;
		if (CommonTools.isNull(filePath) || CommonTools.isNull(fileName)) {
			log.error("��ȡ�ļ�·�����ļ�����Ϊ��; \n fileName === " + fileName
					+ "\n  filePath===" + filePath);
			throw new IllegalArgumentException("��ȡ�ļ�·�����ļ�����Ϊ��");
		}
		try {
			fileStream = new FileInputStream(filePath + fileName);
		} catch (IOException ex) {
			throw new IOException("��ȡ�ļ�ʧ��");
		}
		return fileStream;
	}

	/**
	 * �ƶ��ļ�����ָ����Ŀ¼
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
			throw new IOException("��ȡ�ļ�ʧ��");
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
			// TODO �Զ����� catch ��
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
