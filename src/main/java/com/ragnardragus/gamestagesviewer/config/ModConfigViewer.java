package com.ragnardragus.gamestagesviewer.config;

import com.ragnardragus.gamestagesviewer.util.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = Reference.MOD_ID, name = "stageviewerconfig", type = Type.INSTANCE)
@LangKey("config.stageviewerconfig.title")
public class ModConfigViewer {

	@LangKey(Reference.MOD_ID + ".config.stageguititle")
	@Comment({ "Set title of stages title gui" })
	public static String stageGuiTitle = "Game Stages";

	@LangKey(Reference.MOD_ID + ".config.nostagemessage")
	@Comment({ "Set the message when there is no stages" })
	public static String noStagesMessage = "No Stages";

	@LangKey(Reference.MOD_ID + ".config.stagestaby")
	@Comment({ "Set the Y offset of stages tab" })
	public static int stageTabYoffset = 26;
	
	@LangKey(Reference.MOD_ID + ".config.stagevalues")
	@Comment({ "Set stage, title and icon index \nExample: stageone:Build Master:0" })
	public static String[] newStageTitles = {"stageone:Build Master:0"};
}
