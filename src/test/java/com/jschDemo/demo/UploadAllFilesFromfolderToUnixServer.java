package com.jschDemo.demo;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class UploadAllFilesFromfolderToUnixServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String privateKeyPath = System.getProperty("user.dir") + "\\privateKeyForUnix.ppk";

		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath, "1234");
		Session session = jsch.getSession("ubuntu", "3.6.37.223", 22);

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();

		Channel channel = session.openChannel("sftp");
		channel.connect();

		File localFolder = new File("C:\\Software\\Files");
		if (localFolder.exists()) {

			Collection<File> fileList = FileUtils.listFiles(localFolder, TrueFileFilter.TRUE, null);
			((ChannelSftp) channel).mkdir("multipleFilesUpload");
			for (File file : fileList) {
				((ChannelSftp) channel).put(file.getAbsolutePath(), "multipleFilesUpload");
				System.out.println("Files uploaded" + file.getAbsolutePath());
			}
		}

	}

}
