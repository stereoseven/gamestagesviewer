package com.ragnardragus.gamestagesviewer.client.gui.button;

import com.ragnardragus.gamestagesviewer.client.gui.InventoryTabHandler;
import com.ragnardragus.gamestagesviewer.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.function.Predicate;

public class GuiButtonInventoryTab2 extends GuiButton {

	private final Predicate<GuiScreen> selectedPred;
	public final TabType type;

	public GuiButtonInventoryTab2(int id, int x, int y, TabType type, Predicate<GuiScreen> selectedPred) {
		super(id, x, y, 32, 28, "");
		this.type = type;
		this.selectedPred = selectedPred;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float f) {
		enabled = !mc.player.getRecipeBook().isGuiOpen();

		GuiScreen curr = mc.currentScreen;
		if (curr instanceof GuiContainerCreative
				&& ((GuiContainerCreative) curr).getSelectedTabIndex() != CreativeTabs.INVENTORY.getTabIndex()) {
			enabled = false;
		}

		if (enabled) {
			GlStateManager.color(1F, 1F, 1F);
			mc.renderEngine.bindTexture(Reference.SKILLS_RES);

			int x = this.x;
			int y = this.y;
			int u = 176;
			int v = 0;
			int w = width;
			int h = height;
			
            if (isSelected()) {
                x += 4;
                u += w;
            }

			drawTexturedModalRect(x, y, u, v, w, h);
			drawTexturedModalRect(this.x + 12, y + 6, 176 + type.iconIndex * 16, 28, 16, 16);
			
            if (mouseX > this.x && mouseY > this.y && mouseX < this.x + width && mouseY < this.y + height) {
                InventoryTabHandler.tooltip = new TextComponentTranslation("gamestagesviewer.tab." + type.name().toLowerCase()).getUnformattedComponentText();
                InventoryTabHandler.mx = mouseX;
                InventoryTabHandler.my = mouseY;
            }
		}
	}

    public boolean isSelected() {
        return selectedPred.test(Minecraft.getMinecraft().currentScreen);
    }
	
	public enum TabType {
		INVENTORY(1), STAGES(0);

		public final int iconIndex;

		private TabType(int iconIndex) {
			this.iconIndex = iconIndex;
		}
	}
}
