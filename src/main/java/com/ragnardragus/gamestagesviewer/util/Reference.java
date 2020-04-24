package com.ragnardragus.gamestagesviewer.util;

import net.minecraft.util.ResourceLocation;

public class Reference {
	
	public static final String MOD_ID = "gamestagesviewer";
	public static final String NAME = "GameStages Viewer";
	public static final String VERSION = "0.1";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String CLIENT_PROXY_CLASS = "com.ragnardragus.gamestagesviewer.client.base.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "com.ragnardragus.gamestagesviewer.base.CommonProxy";
	public static final String DEPENDENCES = "required-after:gamestages;required-after:resourceloader";
	public static final ResourceLocation SKILLS_RES = new ResourceLocation(Reference.MOD_ID, "textures/gui/stages.png");
}
