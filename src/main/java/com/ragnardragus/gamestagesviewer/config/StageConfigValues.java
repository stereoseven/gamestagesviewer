package com.ragnardragus.gamestagesviewer.config;

public class StageConfigValues {

	private String stageTitle;
	private int stageIconIndex;

	public StageConfigValues(String stageName, String[] configStages) {
		this.stageTitle = getStageTitle(stageName, configStages);
		this.stageIconIndex = getStageIconIndex(stageName, configStages);
	}

	private String getStageTitle(String stageName, String[] configStages) {

		for (int i = 0; i < configStages.length; i++) {
			String[] splited = configStages[i].split(":");
			if (splited[0].equals(stageName)) return splited[1];
		}
		return stageName;
	}
	
	private int getStageIconIndex(String stageName, String[] configStages) {
		
		for (int i = 0; i < configStages.length; i++) {
			String[] splited = configStages[i].split(":");
			
			if (splited[0].equals(stageName) && splited.length > 2) {
				try {
					return Integer.parseInt(splited[2]);
				} catch (NumberFormatException e) {
					return -1;
				}
			}
		}
		
		return -1;
	}

	public String getStageTitle() {
		return stageTitle;
	}

	public int getStageIconIndex() {
		return stageIconIndex;
	}
}
