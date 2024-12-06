package net.mcreator.justctgui.procedures;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

public class ScriptsWriterProcedure {
	public static void execute() {
		File script = new File("");
		script = new File("scripts", File.separator + (("generated" + new java.text.DecimalFormat("####").format(JustCtguiModVariables.generated_count)) + ".zs"));
		try {
			FileWriter scriptwriter = new FileWriter(script);
			BufferedWriter scriptbw = new BufferedWriter(scriptwriter);
			{
				scriptbw.write(JustCtguiModVariables.Generated_recipe);
			}
			scriptbw.close();
			scriptwriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		JustCtguiModVariables.generated_count = JustCtguiModVariables.generated_count + 0.5;
	}
}
