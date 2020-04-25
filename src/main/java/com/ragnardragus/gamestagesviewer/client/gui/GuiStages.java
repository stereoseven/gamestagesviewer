package com.ragnardragus.gamestagesviewer.client.gui;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import com.ragnardragus.gamestagesviewer.config.ModConfigViewer;
import com.ragnardragus.gamestagesviewer.config.StageConfigValues;
import com.ragnardragus.gamestagesviewer.util.Reference;

import net.darkhax.gamestages.GameStageHelper;
import net.darkhax.gamestages.data.IStageData;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;

public class GuiStages extends GuiScreen {

	final IStageData info;

	private ArrayList<String> stages = new ArrayList<String>();

	private int guiWidth, guiHeight;
	private int left;
	private int top;
	private int indexdisplayed = 0;

	public GuiStages(EntityPlayer player) {
		this.info = GameStageHelper.getPlayerData(player);
	}

	@Override
	public void initGui() {
		guiWidth = 176;
		guiHeight = 166;

		buttonList.clear();
		InventoryTabHandler.addTabs(this, buttonList);

		stages = new ArrayList<String>(this.info.getStages());
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		mc.renderEngine.bindTexture(Reference.SKILLS_RES);
		GlStateManager.color(1F, 1F, 1F);

		left = width / 2 - guiWidth / 2;
		top = height / 2 - guiHeight / 2;

		drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);

		if (stages.size() > 5 && (indexdisplayed + 5) < stages.size()) {
			drawTexturedModalRect(left + 79, top + 155, 0, 198, 16, 16);
		}

		super.drawScreen(mouseX, mouseY, partialTicks);

		if (stages.size() > 0) {

			int fontYOffset = 0;
			int iconOffset = 0;
			int stageLayoutOffset = 0;
			for (int i = 0 + indexdisplayed; i < stages.size(); i++) {

				// Stage Layout
				if (i < 5 + indexdisplayed) {
					mc.renderEngine.bindTexture(Reference.SKILLS_RES);
					drawTexturedModalRect(left + 10, top + 16 + stageLayoutOffset, 0, 166, 156, 26);

					stageLayoutOffset += 28;

					int stageIconIndex = getStageConfigValues(stages.get(i), ModConfigViewer.newStageTitles)
							.getStageIconIndex();
					if (stageIconIndex != -1) {
						int iconX = getIconXByStage(stageIconIndex);
						int iconY = getIconYByStage(stageIconIndex);

						drawTexturedModalRect(left + 16, top + 21 + iconOffset, 176 + iconX, 44 + iconY, 16, 16);
					}
					iconOffset += 28;

					String stageTitle = getStageConfigValues(stages.get(i), ModConfigViewer.newStageTitles)
							.getStageTitle();
					mc.fontRenderer.drawString(stageTitle, left + 36, top + 29 + fontYOffset - 4, 0xFFFFFF);
					fontYOffset += 28;
				}
			}

		} else {
			String noStagesText = ModConfigViewer.noStagesMessage;
			mc.fontRenderer.drawString(noStagesText, width / 2 - getTextWidth(noStagesText) / 2, height / 2, 0x333300);
		}

		String tileText = ModConfigViewer.stageGuiTitle;
		mc.fontRenderer.drawString(tileText, width / 2 - getTextWidth(tileText) / 2, top + 4, 0x333300);
	}

	private int getTextWidth(String text) {
		FontRenderer rederer = mc.fontRenderer;
		return rederer.getStringWidth(text);
	}

	private int getIconXByStage(Integer index) {
		if (index >= 0 && index < 8) {
			return 0;
		} else if (index >= 9 && index < 16) {
			return 16;
		} else if (index >= 16 && index < 24) {
			return 16;
		} else {
			return 48;
		}
	}

	private int getIconYByStage(Integer index) {
		if (index >= 0 && index < 31) {
			return (index % 8) * 16;
		}
		return 0;
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		if (Mouse.getEventDWheel() > 0) {
			if (stages.size() > 5 && indexdisplayed > 0 && (indexdisplayed - 1) >= 0) {
				indexdisplayed -= 1;
			}
		} else if (Mouse.getEventDWheel() < 0) {
			if (stages.size() > 5 && indexdisplayed >= 0 && (indexdisplayed + 1) <= stages.size()
					&& (stages.size() - indexdisplayed) > 5) {
				indexdisplayed += 1;
			}
		}
	}

	private StageConfigValues getStageConfigValues(String stagename, String[] titles) {
		return new StageConfigValues(stagename, titles);
	}
}
