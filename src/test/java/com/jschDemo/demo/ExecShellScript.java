package com.jschDemo.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ExecShellScript {

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

		Channel channel = session.openChannel("exec");

		((ChannelExec) channel).setCommand("chmod 777 script.sh && ./script.sh");
		((ChannelExec) channel).setErrStream(System.err);
		channel.connect();

		// to read console output
		InputStream in = channel.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		
		System.out.println("exit status is -> "+channel.getExitStatus());
		while(!channel.isClosed())
		{
			Thread.sleep(250);
		}

		channel.disconnect();
		session.disconnect();

	}

}
