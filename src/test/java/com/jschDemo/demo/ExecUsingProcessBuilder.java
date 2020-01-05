package com.jschDemo.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecUsingProcessBuilder {

	public static void main(String[] args) throws Exception {

		ProcessBuilder pb = new ProcessBuilder("C:\\Software\\aws\\receiveFromHost.bat");
		Process process = pb.start();
		System.out.println("process completed");
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		System.out.println(sb);
		process.exitValue();

	}

}
