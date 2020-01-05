package com.jschDemo.demo;

import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class CheckIfFileExistsOnUnixServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

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

		Vector<LsEntry> files = ((ChannelSftp) channel).ls("/home/ubuntu/");
		//System.out.println("pwd: "+((ChannelSftp) channel).pwd());
		System.out.println(files.get(0).getFilename());
		int fileCount = files.size();
		for(int i=0;i<fileCount;i++)
		{
			
			if(files.get(i).getFilename().equals("Desert.jpg"))
			{
				
				System.out.println("file is present");
				channel.disconnect();
				session.disconnect();
				break;
			}
			if(i==fileCount-1)
			{
				System.out.println("file is not present");
				channel.disconnect();
				session.disconnect();
				break;
			}
		}

	}

}
