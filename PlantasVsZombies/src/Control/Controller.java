package Control;
import Logic.*;
import java.util.Scanner;
import Logic.GamePrinter;
import Logic.MyStringUtils.MyStringUtils;

public class Controller {
	private Game game;
	private Scanner sc;
	private	String[] Command;//,scX,scY,scT;
	private GamePrinter games;
	private boolean end = true;
	private String type;
	private int x;
	private int Dead=0;
	private int Z;
	private int Kills;
	private int y;
	private int cycle=0;
	private boolean WhoWins;
	private boolean aux;
	private boolean exit = false;
	private boolean bucle = false;
	
	

	public  Controller(Game game) {
		this.game = game;
		
	}
	public void run () {
		PrintTitle();
		PrintGame();
		getTotalzombies();
		getnumerozombies();
		while(end) {	
			if(update()) {
				Draw();
				do{GetScanner();
				do Comandos();
				while(aux);
				if(!exit) {
				comprobar();
				comprobarwin();
				doGame();
				}}while(bucle);
			}
		}
		Resultado();
			
			
	}
	
	public void FirstPrint() {
		System.out.println("Number of cycles:" + cycle);
		System.out.println("Sun coins: " + game.getsolManager());
		System.out.println("Remaining zombies: " + Z);
		cycle++;
	}
	
	public void Print() {
		System.out.println(games);
		System.out.println("Number of cycles:" + cycle);
		System.out.println("Sun coins: " + game.getsolManager());
		System.out.println("Remaining zombies: " + Z);
		System.out.print("Command > ");
		
	}
	public void Comandos() {
		switch(Command[0]) {
			case "H":
			case "HELP":
				DrawHelp();
				System.out.print("Command >");
				GetScanner();
				bucle = false;
				aux = true;
				break;
			case "L":
			case "LIST":
				DrawList();
				System.out.print("Command >");
				GetScanner();
				aux = true;
				bucle = false;
				break;
			case "E":
			case "EXIT":
				exit = true;
				end = false;
				aux = false;
				bucle = false;
				
			default:
				aux = false;
				
	}
	}
	public void GetScanner() {
		
		this.sc = new Scanner(System.in);
		String aux = sc.nextLine();
		aux =aux.toUpperCase();
		this.Command = aux.split(" ");
		
		
		
	}
	public void comprobar() {
		boolean ok= false;
		boolean aux = false;
		do{
		if (Command.length != 1) {
			aux = false;
		if(Command.length == 4) {
			x = Integer.parseInt(Command[2]);
			y = Integer.parseInt(Command[3]);
		}
		while((x > 3 || x < 0 || y > 7 || y < 0) ||( (Command.length == 2 || Command.length == 3))&& !ok && (!Command[0].equals("A") || !Command[0].equals("R")))
		{
			System.out.println("Unknown command");
			System.out.print("Command > ");
			GetScanner();
			if(Command.length == 1) {
				ok = true;
			}
			if(Command[0].equals(""))
				break;
			else if(Command.length == 2 || Command.length == 3) {
				ok = false;
			}
			else if (Command[0].equals("R") || Command[0].equals("RESET")) {
				break;
			}
			else {
				x = Integer.parseInt(Command[2]);
				y = Integer.parseInt(Command[3]);
			}
		}
		}
		else {
			if(Command[0].equals("") || Command[0].equals("R") || Command[0].equals("RESET") )
				aux = false;
			else { aux = true;
			System.out.println("Unkown command");
			System.out.print("Command > ");
			GetScanner();
			}
		}
		}while(aux);
		
	}
	public void doGame() {
		switch(this.Command[0]) {
		case "A":
		case "ADD":
			if(Command[1].equals("P") || Command[1].equals("S")) {
				type = Command[1];
				game.add(type,x,y);
				postUpdate();
				bucle = false;
				}
			else {
				System.out.println("Invalid object");
				System.out.print("Command >");
				bucle = true;
			}
				break;
		case "R":
		case "RESET":
			game.reset();
			cycle = 0;
			getnumerozombies();
			games = new GamePrinter(this.game,game.dimX,game.dimY);
			bucle = false;
				break;
		default: 
			postUpdate();
			bucle = false;
			
			break;
			}
		
		
	}
	public void DrawList() {
		System.out.println();
		System.out.println("[S]unflower: Cost: 20 suncoins Harm: 0 ");
		System.out.println("[P]eashooter: Cost: 50 suncoins Harm: 1 ");
		System.out.println();
	}
	public void DrawHelp() {
		System.out.println();
		System.out.println("Add <plant> <x> <y>: Adds a plant in position x, y. ");
		System.out.println("List: Prints the list of available plants. ");
		System.out.println("Reset: Starts a new game.");
		System.out.println("Help: Prints this help message.");
		System.out.println("Exit: Terminates the program.");
		System.out.println("[none]: Skips cycle");
		System.out.println();
	}
	public void postUpdate() {
		game.GotZombie();
		getnumerozombies();
		game.ActualizarGirasoles();
		game.updateturnZombie();
		cycle++;
	}
	public boolean update() {
		game.bajarvidaZombie();
		game.ManageSol();
		if(game.mover()) {
			this.end = false;
			this.WhoWins= false;
			return false;
		}
		else return true;
	}
	public void Deads() {
		Dead = game.getMuertos();
	}
	public void PrintGame() {
		games = new GamePrinter(this.game,game.dimX,game.dimY);
	} 
	public void getTotalzombies() {
		Kills = game.getnumerozombies();
	}
	public boolean comprobarwin() {
		if(Kills == Dead) {
			WhoWins = true;
			return end = false;
		}
		else {
			return end = true;
			}
		
	}
	public void Draw() {
		Deads();
		PrintGame();
		Print();
	}

	public void Resultado() {
		if (exit == true)
			System.out.println("Exit Done");
		else if(WhoWins == true)
			System.out.println("Players Win");
		else
			System.out.println("Zombies Win");
		}
public void getnumerozombies() {
	Z = game.getnumerozombies();
}
public String toString() {
	String pVsZ = "++++++ WELCOME TO ++++++";
	int marginSize = 40;
	String space = " ";
	String margin = MyStringUtils.repeat(space, marginSize);
	StringBuilder str = new StringBuilder();
	str.append(margin).append(pVsZ);
	return str.toString();
}
public void PrintTitle() {
	System.out.println();
	System.out.println(toString());
	
	System.out.println("______  _                _                _   _       ______                   _      _            \r\n" + 
			"| ___ \\| |              | |              | | | |     |___  /                  | |    (_)           \r\n" + 
			"| |_/ /| |  __ _  _ __  | |_   __ _  ___ | | | | ___    / /   ___   _ __ ___  | |__   _   ___  ___ \r\n" + 
			"|  __/ | | / _` || '_ \\ | __| / _` |/ __|| | | |/ __|  / /   / _ \\ | '_ ` _ \\ | '_ \\ | | / _ \\/ __|\r\n" + 
			"| |    | || (_| || | | || |_ | (_| |\\__ \\\\ \\_/ /\\__ \\./ /___| (_) || | | | | || |_) || ||  __/\\__ \\\r\n" + 
			"\\_|    |_| \\__,_||_| |_| \\__| \\__,_||___/ \\___/ |___/\\_____/ \\___/ |_| |_| |_||_.__/ |_| \\___||___/\r\n" + 
			"                                                                                                   ");
	System.out.println();
}
}

