package main;

public enum GameStates {
	PLAYING,
	EDIT,
	MENU,
	SETTINGS;
	
	public static GameStates gameState = MENU;
	public static void SetGameState(GameStates game) {
		gameState = game;
	}
}
