package com.jschDemo.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SendFileToUnixServer {

	public static void main(String[] args) throws Exception {
		
		
String privateKeyPath=System.getProperty("user.dir")+"\\privateKeyForUnix.ppk";
		
		JSch jsch=new JSch();
		jsch.addIdentity(privateKeyPath,"1234");
		Session session=jsch.getSession("ubuntu", "3.6.37.223", 22);
		
	
	
		Properties config=new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		
		Channel channel=session.openChannel("sftp");
		channel.connect();
		((ChannelSftp) channel).put("C:\\Software\\fileToUpload.txt", "/home/ubuntu");
		

		

		//to read console output
		InputStream in =channel.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		String line;
		while((line=reader.readLine()) != null)
		{
			System.out.println(line);
		}
		
		((ChannelSftp) channel).disconnect();
		channel.disconnect();
		session.disconnect();

	}

}
