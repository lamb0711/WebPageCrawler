package edu.handong.csee.java.bonus;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {
	String output;
	String url;
	boolean verbose;
	boolean help;

	public static void main(String[] args) {
		Main my = new Main();
		my.run(args);
	}

	private void run(String[] args) {
		Options options = createOptions();

		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}

			URLParser pa = new URLParser();
			try {
				pa.saveUrlLine(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileWriter fi = new FileWriter();
			fi.saveInHtmlFile(output);

			System.out.println("You provided \"" +url + "\" as the value of the option u");


			if(verbose) {
				System.out.println("Your program is terminated. (This message is shown because you turned on -v option!");
			}
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			url = cmd.getOptionValue("u");
			output = cmd.getOptionValue("d");

			verbose = cmd.hasOption("v");
			help = cmd.hasOption("h");

		}catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}

	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Web page Crawler";
		String footer ="\nPlease report issues at https://github.com/lamb0711/WebPageCrawler/issues";
		formatter.printHelp("Web page Crawler", header, options, footer, true);
	}

	private Options createOptions() {
		Options options = new Options();


		options.addOption(Option.builder("u").longOpt("url")
				.desc("Set a address of url")
				.hasArg()
				.argName("Path name to display")
				.required()
				.build());
		options.addOption(Option.builder("d").longOpt("output")
				.desc("Set a path of a directory or a file to output")
				.hasArg()
				.argName("Path name to display")
				.required()
				.build());
		options.addOption(Option.builder("v").longOpt("verbose")
				.desc("Display detailed messages!")
				//.hasArg()     // this option is intended not to have an option value but just an option
				.argName("verbose option")
				//.required() // this is an optional option. So disabled required().
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		return options;
	}

}
