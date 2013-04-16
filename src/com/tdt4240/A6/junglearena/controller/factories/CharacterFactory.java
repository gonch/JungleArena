package com.tdt4240.A6.junglearena.controller.factories;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.characters.*;

///**
// * @author hengsti
// */
//public class CharacterFactory {
//	private static List<String> gameCharacters;
//	private final static String pkgPath = "com.tdt4240.A6.junglearena.model.characters";
//	private static CharacterFactory instance = new CharacterFactory();
//
//	private CharacterFactory() {
//	}
//
//	/**
//	 * @return the instance of this Class (Singelton)
//	 */
//	public static CharacterFactory getInstance() {
//		gameCharacters = new ArrayList<String>();
//		loadCharacters(pkgPath);
//		return instance;
//	}
//
//	/**
//	 * Load all characters from the according package.
//	 * 
//	 * @param pkgPath
//	 */
//	private static void loadCharacters(String pkgPath) {
//		Class<?>[] classes = ClassReader.readFromPackage(pkgPath);
//		for (Class<?> c : classes) {
//			String[] fullClassName = c.getName().split("\\.");
//			String className = fullClassName[fullClassName.length - 1];
//			if (!className.equals("GameCharacter")) {
//				gameCharacters.add(className);
//			}
//		}
//	}
//
//	/**
//	 * @return a deep copied list of the character available
//	 */
//	public String[] getCharacters() {
//		String[] copyOfCharacters = new String[gameCharacters.size()];
//		for (int i = 0; i < gameCharacters.size(); i++) {
//			copyOfCharacters[i] = new String(gameCharacters.get(i));
//		}
//		return copyOfCharacters;
//	}
//
//	/**
//	 * @param type
//	 *            of character; Use getCharacters() and chose one.
//	 * @param health
//	 * @param name
//	 * @param pos
//	 * @param skin
//	 * @return a new instance of the specified character
//	 */
//	public GameCharacter createCharacter(String type, int health, String name, Vector2 pos, String skin) {
//		String className = pkgPath + "." + type;
//		for (String s : gameCharacters) {
//			if (s.equals(type)) {
//				try {
//					@SuppressWarnings("unchecked")
//					Class<GameCharacter> gameCharacter = (Class<GameCharacter>) Class.forName(className);
//					GameCharacter newInstanceOfCharacter = gameCharacter.getConstructor(Integer.TYPE, String.class, Vector2.class,
//							String.class).newInstance(health, name, pos, skin);
//					return newInstanceOfCharacter;
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		System.err.print("Invalid Character! Can not create \"" + className + "\"! Created default instead.");
//		return new Monkey(health, name, pos, skin);
//	}
//
//}

public class CharacterFactory {
	private static List<String> characters;
	private final String pkgPath = "com.tdt4240.A6.junglearena.model.characters";

	private CharacterFactory() {
		characters = new ArrayList<String>();
		characters.add("Elephant");
		characters.add("Monkey");
		characters.add("Lion");
	}


	/**
	 * SingletonHolder is loaded on the first execution of
	 * Singleton.getInstance() or the first access to SingletonHolder.INSTANCE,
	 * not before.
	 */
	private static class SingletonHolder {
		public static final CharacterFactory INSTANCE = new CharacterFactory();
	}

	public static CharacterFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public String[] getCharacters() {
		String[] copyOfCharacters = new String[characters.size()];
		for(int i=0; i<characters.size();i++){
			copyOfCharacters[i]= new String (characters.get(i));
		}
		return copyOfCharacters;
	}

	public GameCharacter createCharacter(String type, int health, String name, Vector2 pos, String skin) {
		String className = pkgPath +"."+ type;
		for (String s : characters) {
			if (s.equals(type)) {
				try {
					@SuppressWarnings("unchecked")
					Class<GameCharacter> weapon = (Class<GameCharacter>) Class.forName(className);
					GameCharacter newInstanceOfCharacter = weapon.getConstructor(Integer.TYPE, String.class, Vector2.class,
							String.class).newInstance(health, name, pos, skin);
					return newInstanceOfCharacter;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.err.print("Invalid Character! Can not create \"" + className + "\"! Created default instead.");
		return new Monkey(health, name, pos, skin);
	}

}
