package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import environment.Environment;
import infini.EnvInf;
import frog.Frog;
import infini.FrogInf;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import java.util.Scanner;
import frog.Frog;

public class Main {

	public static void main(String[] args) {

		//Caract�ristiques du jeu
		int width = 26;
		int height = 30;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.2;
		int modeJeu = 0;


		System.out.print( " ----------------------- MENU -----------------------\n");

		System.out.print( "Veuillez choisir un mode de jeu  \n");

		try ( Scanner scanner = new Scanner( System.in ) ) {
			System.out.print("Mode de jeu classique : tapez 1  \nMode de jeu infini : tapez 2 \n");
			modeJeu = scanner.nextInt();
		}





		/*do{
			System.out.print( " ----------------------- MENU -----------------------\n");

			System.out.print( "Veuillez choisir un mode de jeu  \n");

			try ( Scanner scanner = new Scanner( System.in ) ) {
				System.out.print( "Mode de jeu classique : tapez 1  \nMode de jeu infini : tapez 2 \n");
				modeJeu = scanner.nextInt();
			}

			switch(modeJeu) {
				case 1:
					//Création et liason de la grenouille pour le mode de jeu classique
					IFrog frog = new Frog(game);
					game.setFrog(frog);
					graphic.setFrog(frog);
					//Création et liaison de l'environnement classique
					IEnvironment env = new Environment(game);
					game.setEnvironment(env);
					break;

				case 2:
					//Création et liason de la grenouille pour le mode de jeu infini
					IFrog frog2 = new FrogInf(game);
					game.setFrog(frog2);
					graphic.setFrog(frog2);
					//Création et liaison de l'environnement infini
					IEnvironment env2 = new EnvInf(game);
					game.setEnvironment(env2);
					break;
				default:
					System.out.println("Entrez un choix entre 1 et 2");
					break;
			}
		} while(modeJeu < 1 || modeJeu > 2);
*/

/*
		//Cr�ation de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Cr�ation de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
*/
		switch(modeJeu) {
			case 1:
				//Cr�ation de l'interface graphique
				IFroggerGraphics graphic = new FroggerGraphic(width, height);
				//Cr�ation de la partie
				Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
				//Création et liason de la grenouille pour le mode de jeu classique
				IFrog frog = new Frog(game);
				game.setFrog(frog);
				graphic.setFrog(frog);
				//Création et liaison de l'environnement classique
				IEnvironment env = new Environment(game);
				game.setEnvironment(env);
				Timer timer = new Timer(tempo, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						game.update();
						graphic.repaint();
					}
				});
				timer.setInitialDelay(0);
				timer.start();
				break;

			case 2:
				//Cr�ation de l'interface graphique
				IFroggerGraphics graphic2 = new FroggerGraphic(width, height);
				//Cr�ation de la partie
				Game game2 = new Game(graphic2, width, height, minSpeedInTimerLoops, defaultDensity);
				//Création et liason de la grenouille pour le mode de jeu infini
				IFrog frog2 = new FrogInf(game2);
				game2.setFrog(frog2);
				graphic2.setFrog(frog2);
				//Création et liaison de l'environnement infini
				IEnvironment env2 = new EnvInf(game2);
				game2.setEnvironment(env2);
				Timer timer2 = new Timer(tempo, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						game2.update();
						graphic2.repaint();
					}
				});
				timer2.setInitialDelay(0);
				timer2.start();
				break;
			default:
				System.out.println("Entrez un choix entre 1 et 2");
				try ( Scanner scanner = new Scanner( System.in ) ) {
					System.out.print("Mode de jeu classique : tapez 1  \nMode de jeu infini : tapez 2 \n");
					modeJeu = scanner.nextInt();
				}
				break;
		}


		/*if(modeJeu == 1){
			//Création et liason de la grenouille pour le mode de jeu classique
			IFrog frog = new Frog(game);
			game.setFrog(frog);
			graphic.setFrog(frog);
			//Création et liaison de l'environnement classique
			IEnvironment env = new Environment(game);
			game.setEnvironment(env);
		}else if(modeJeu == 2){
			//Création et liason de la grenouille pour le mode de jeu infini
			IFrog frog = new FrogInf(game);
			game.setFrog(frog);
			graphic.setFrog(frog);
			//Création et liaison de l'environnement infini
			IEnvironment env = new EnvInf(game);
			game.setEnvironment(env);
		}*/
/*
		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();*/
	}
}
