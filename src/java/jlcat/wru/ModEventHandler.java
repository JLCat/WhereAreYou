package jlcat.wru;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandler {

	private static ModEventHandler instance = new ModEventHandler();

	private ModEventHandler() {
	}

	public static ModEventHandler getInstance() {
		return instance;
	}

	@SubscribeEvent
	public void onRenderGameOverlayEventPre(final RenderGameOverlayEvent.Post event) {
		if (event.getType().equals(ElementType.HOTBAR)) {

			if (WhereAreYou.instance.showGameInfo)
				GuiGameInfo.getInstance().displayGameInfo();
			if (WhereAreYou.instance.showPlayerIcon)
				GuiPlayerIcon.getInstance().displayPlayerIcon();
		}
	}
}
