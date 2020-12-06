package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;

import environment.Environment;
import frog.Frog;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import infini.EnvInf;
import infini.FrogInf;
import multijoueur.FrogMulti;
import multijoueur.GameMulti;
import multijoueur.env.EnvMulti;

public class Main {

	public static void main(String[] args) {

		//Caract�ristiques du jeu
		int width = 26;
		int height = 30;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.2;

//Caractérisques du menu
		int modeJeu = 0;
		int choix;
		int saisie = 0;


		System.out.print( " ----------------------- MENU -----------------------\n");
		System.out.print( "Veuillez choisir un mode de jeu  \n");
		Scanner scanner = new Scanner( System.in );
		System.out.print("Mode de jeu classique : tapez 1  \nMode de jeu infini : tapez 2 \nMode de jeu multijoueur : tapez 3  \nQuitter : tapez 4 \n");
		choix = scanner.nextInt();

		do{

			if(modeJeu != 0){
				choix = saisie;
			}
			switch(choix) {
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
				case 3:
					IFroggerGraphics graphic3 = new FroggerGraphic(width, height);
					//Cr�ation de la partie
					//Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
					GameMulti gameMulti = new GameMulti(graphic3, width, height, minSpeedInTimerLoops, defaultDensity);
					//Cr�ation et liason de la grenouille
					IFrog frog3 = new FrogMulti(gameMulti, width/2 +5);
					IFrog frog4 = new FrogMulti(gameMulti, width/2 -5);
					//IFrog frog = new FrogInf(game);
					gameMulti.setFrog(frog3, frog4);
					graphic3.setFrog2(frog3, frog4);
					//Cr�ation et liaison de l'environnement
					IEnvironment env3 = new EnvMulti(gameMulti);
					gameMulti.setEnvironment(env3);


					//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
					Timer timer3 = new Timer(tempo, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							gameMulti.update();
							graphic3.repaint();
						}
					});
					timer3.setInitialDelay(0);
					timer3.start();
					break;

				case 4:
					break;

				default:
					modeJeu++;
					System.out.println("Entrez un choix entre 1 et 2");
					Scanner clavier = new Scanner(System.in);
					System.out.print("Mode de jeu classique : tapez 1  \nMode de jeu infini : tapez 2 \n");
					saisie = clavier.nextInt();
					break;
			}
		}while(choix != 2 && choix != 1 && choix != 3 && choix != 4);

	}
}

