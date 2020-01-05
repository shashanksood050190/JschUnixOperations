package com.jschDemo.demo;

import java.io.OutputStreamWriter;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.WriterStreamConsumer;

public class ExecUsingPlexusUtil {

	public static void main(String[] args) throws Exception {

		Commandline cmd = new Commandline();
		cmd.setExecutable("C:\\Software\\aws\\receiveFromHost.bat");

		WriterStreamConsumer systemOut = new WriterStreamConsumer(new OutputStreamWriter(System.out));
		WriterStreamConsumer systemErr = new WriterStreamConsumer(new OutputStreamWriter(System.out));

		int returnCode = CommandLineUtils.executeCommandLine(cmd, systemOut, systemErr);
		if (returnCode == 0) {
			System.out.println("successful");
		} else {
			System.out.println("unsuccessful");
		}

	}

}
