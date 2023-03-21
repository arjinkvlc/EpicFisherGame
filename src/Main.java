/**
 * @author:Arjin Kavalci
 * @since :27.07.2022
 */

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args)  {
        FishGame game=new FishGame();
    }
}
class FishGame {
    Scanner input = new Scanner(System.in);
    String enterScreenInput;
    String returnerCheck;
    String marketCategoryInput;
    boolean haveCat=false;
    HashSet<String> achievements = new HashSet<>(5);
    HashSet<String> achievementsUnlocked = new HashSet<>(5);
    ArrayList<Fish> fishes = new ArrayList<>(8);
    ArrayList<MarketProducts> products = new ArrayList<>();
    ArrayList<MarketProducts> inventoryItems = new ArrayList<>();
    HashMap<String, Integer> caughtFishes = new HashMap<>();
    Inventory myInventory = new Inventory(0);

    public FishGame() {
        identifyFishes();
        identifyAchievements();
        identifyMarketProducts();
        File dosya = new File("progress/wallet.txt");
        try {
            if (!dosya.exists()) {
                dosya.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(dosya, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            FileReader fileReader = new FileReader(dosya);
            BufferedReader reader = new BufferedReader(fileReader);
            if (reader.readLine()==null)
                writer.write("0\n");
            writer.close();
            String line = null;
            String lastLine = null;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
            reader.close();
            int lastNumber = Integer.parseInt(lastLine.trim());
            myInventory.wallet= lastNumber;
            if (myInventory.wallet==-1)
                myInventory.wallet=0;
            writer.write(myInventory.wallet+"\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Dosya oluşturulurken bir hata oluştu.");
        }
        GUI();


    }

    private void GUI()  {
        while (true) {
            System.out.println(ConsoleColors.WHITE_BACKGROUND + ConsoleColors.BLUE_BOLD_BRIGHT + "********** EPIC FISHER GAME **********" + ConsoleColors.RESET + ConsoleColors.GREEN_BOLD_BRIGHT);
            System.out.println("1) Go Fishing");
            System.out.println("2) Market");
            System.out.println("3) Inventory");
            System.out.println("4) Achievments");
            System.out.println("5) Credits");
            System.out.println("S) Save Progress" + ConsoleColors.RESET);
            enterScreenInput = input.next();
            switch (enterScreenInput) {
                case "1" -> goFishing();
                case "2" -> goMarket();
                case "3" -> goInventory();
                case "4" -> goAchievements();
                case "5" -> goCredits();
                case "S", "s" -> saveProgress();
                default -> System.out.println("Wrong input.");
            }
        }
    }

    private void saveProgress() {
        File dosya = new File("progress/wallet.txt");
        try {
            if (!dosya.exists()) {
                dosya.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(dosya, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(myInventory.wallet+"\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Dosya oluşturulurken bir hata oluştu.");
        }
    }

    private void inGameGUI(String locationInput) {
        String inGameInput = "";
        while (!inGameInput.toUpperCase(Locale.ROOT).contains("M")) {
            System.out.println("   /" + ConsoleColors.WHITE_BOLD_BRIGHT + "|" + ConsoleColors.RESET + "                       |  ");
            System.out.println("  /" + ConsoleColors.WHITE_BACKGROUND_BRIGHT + " |" + ConsoleColors.RESET + "        " + ConsoleColors.YELLOW_BOLD + "/" + ConsoleColors.RESET + "|             |    ____________       ________________");
            System.out.println(" /" + ConsoleColors.WHITE_BACKGROUND_BRIGHT + "__|" + ConsoleColors.RESET + "   (\") " + ConsoleColors.YELLOW_BOLD + "/" + ConsoleColors.RESET + " |             |   |   Press    |     |     Press      |");
            System.out.println("    " + ConsoleColors.BLACK_BOLD + "|" + ConsoleColors.RESET + "   |\\\\" + ConsoleColors.YELLOW_BOLD + "/" + ConsoleColors.RESET + "  |             |   | X to catch |     | M to main menu |");
            System.out.println(" " + ConsoleColors.BLACK_BOLD + "___|___" + ConsoleColors.RESET + "/\\" + ConsoleColors.BLACK_BOLD + "__" + ConsoleColors.RESET + "  |             |   |____________|     |________________|");
            System.out.println(" \\" + ConsoleColors.BLACK_BACKGROUND + "_________" + ConsoleColors.RESET + "/" + ConsoleColors.BLUE_BACKGROUND_BRIGHT + "                " + ConsoleColors.RESET + "|  ");
            inGameInput = input.next();
            if (inGameInput.toUpperCase(Locale.ROOT).contains("X")) {
                double fishRate = Math.random() * 100;
                ;
                switch (locationInput) {
                    case "1":
                        if (fishRate < 70)
                            catchFish(fishes.get(0));
                        else
                            catchFish(fishes.get(1));
                        break;
                    case "2":
                        if (fishRate < 80)
                            catchFish(fishes.get(2));
                        else
                            catchFish(fishes.get(3));
                        break;
                    case "3":
                        if (fishRate < 90)
                            catchFish(fishes.get(4));
                        else
                            catchFish(fishes.get(5));
                        break;
                    case "4":
                        if (fishRate < 95)
                            catchFish(fishes.get(6));
                        else
                            catchFish(fishes.get(7));
                        break;
                }
            }
        }
    }

    private void goFishing()  {
        String locationInput = "";
        while (!locationInput.toUpperCase(Locale.ROOT).contains("M")) {
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"Choose a location:");
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "1) Pool  (%0 miss rate)");
            System.out.println(ConsoleColors.BLUE_BOLD + "2) Lake  (%30 miss rate)");
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "3) Sea   (%60 miss rate)");
            System.out.println(ConsoleColors.BLUE_BOLD + "4) Ocean (%90 miss rate)" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"'M' to return main menu.");
            locationInput = input.next();
            switch (locationInput) {
                case "1":
                    inGameGUI(locationInput);
                    break;
                case "2":
                    inGameGUI(locationInput);
                    break;
                case "3":
                    if (inventoryItems.contains(products.get(10)) || inventoryItems.contains(products.get(11)))
                        inGameGUI(locationInput);
                    else
                        System.out.println("You need a " + products.get(10).getName() + " for fishing here.");
                    break;
                case "4":
                    if (inventoryItems.contains(products.get(11)))
                        inGameGUI(locationInput);
                    else
                        System.out.println("You need a " + products.get(11).getName() + " for fishing here.");
                    break;
            }
        }
    }

    private void buyItem(MarketProducts products)  {
        if (inventoryItems.contains(this.products.get(14))&&!haveCat) {
            for (int i = 0; i < this.products.size(); i++) {
                if (Objects.equals(this.products.get(i).getType(), "Food"))
                    this.products.get(i).setPrice((int) (this.products.get(i).getPrice() * 0.75));
            }haveCat=true;
        }
        System.out.println(ConsoleColors.RED_BOLD);
        if (myInventory.wallet >= products.getPrice() && products.type != "Food") {
            System.out.println(products.getExplanation());
            System.out.println("Do you want to buy " + products.getName() + "? (Y/N)");
            String check = input.next();
            if (check.toUpperCase(Locale.ROOT).equals("Y")) {
                if (inventoryItems.isEmpty() && !achievementsUnlocked.contains("Commercial Man")) {
                    achievementsUnlocked.add("Commercial Man");
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Achievement Unlocked: Commercial Man" + ConsoleColors.RESET);
                }
                if (products.getType() == "Pet") {
                    int petCheck = 0;
                    for (int i = 0; i < inventoryItems.size(); i++) {
                        if (inventoryItems.get(i).getType() == "Pet")
                            petCheck = 1;
                    }
                    if (petCheck == 0) {
                        achievementsUnlocked.add("New Friends");
                        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Achievement Unlocked: New Friends" + ConsoleColors.RESET);
                    }
                }
                inventoryItems.add(products);
                myInventory.wallet -= products.getPrice();

                System.out.println("You bought " + products.name + " !");
                System.out.println("Current balance: " + myInventory.wallet + "$");
                int petAmount = 0;
                for (int i = 0; i < inventoryItems.size(); i++) {
                    if (inventoryItems.get(i).getType() == "Pet") {
                        petAmount++;
                    }
                    if (petAmount == 3&&!achievementsUnlocked.contains("Animal Lover")) {
                        achievementsUnlocked.add("Animal Lover");
                        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Achievement Unlocked: Animal Lover" + ConsoleColors.RESET);
                    }
                }
            }
        } else if (products.type == "Food" && myInventory.wallet >= products.getPrice() * 50) {
            System.out.println(products.getExplanation());
            System.out.println("Do you want to buy " + products.getName() + " x50 ? (Y/N)");
            String check = input.next();
            if (check.toUpperCase(Locale.ROOT).equals("Y")) {
                if (inventoryItems.isEmpty() && !achievementsUnlocked.contains("Commercial Man")) {
                    achievementsUnlocked.add("Commercial Man");
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Achievement Unlocked: Commercial Man" + ConsoleColors.RESET);
                }
                for (int i = 0; i < 50; i++) {
                    inventoryItems.add(products);
                }
                myInventory.wallet -= products.getPrice() * 50;

                System.out.println("You bought " + products.name + "x50 !");
                System.out.println("Current balance: " + myInventory.wallet + "$");
            }
        } else {
            System.out.println("You don't have enough money to buy it!");
            System.out.println("Current balance: " + myInventory.wallet + "$" + ConsoleColors.RESET);
        }
    }

    private void catchFish(Fish type)  {
        int missChance = (int) (Math.random() * 100);
        int catchChance = 100;
        int missRate = (int) (Math.random() * 100);
        if (inventoryItems.contains(products.get(13)))
            missRate *= 1.25;
        switch (type.getHabitat()) {
            case "Pool":
                if (caughtFishes.containsKey(type.getType())) {
                    caughtFishes.replace(type.getType(), caughtFishes.get(type.getType()), caughtFishes.get(type.getType()) + 1);
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "You got a/an " + type.getType() + "!" + ConsoleColors.RESET);
                } else {
                    caughtFishes.put(type.getType(), 1);
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "You got a/an " + type.getType() + "!" + ConsoleColors.RESET);
                    if (Objects.equals(type.getType(), "Gold Fish")) {
                        achievementsUnlocked.add("First Rare Fish");
                        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Achievement Unlocked: First Rare Fish" + ConsoleColors.RESET);
                    }
                }
                break;
            case "Lake":

                if (inventoryItems.contains(products.get(7))) {
                    catchChance = 70;
                    for (int i = 0; i < inventoryItems.size(); i++) {
                        if (inventoryItems.get(i) == products.get(6) || inventoryItems.get(i) == products.get(5) || inventoryItems.get(i) == products.get(4) || inventoryItems.get(i) == products.get(3) || inventoryItems.get(i) == products.get(2) || inventoryItems.get(i) == products.get(1))
                            catchChance = 100;
                        else if (inventoryItems.get(i) == products.get(0) && catchChance != 100)
                            catchChance = 85;
                    }
                    if (missChance <= catchChance) {
                        if (caughtFishes.containsKey(type.getType())) {
                            caughtFishes.replace(type.getType(), caughtFishes.get(type.getType()), caughtFishes.get(type.getType()) + 1);
                            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "You got a/an " + type.getType() + "!" + ConsoleColors.RESET);
                            inventoryItems.remove(products.get(7));
                        } else {
                            caughtFishes.put(type.getType(), 1);
                            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "You got a/an " + type.getType() + "!" + ConsoleColors.RESET);
                            inventoryItems.remove(products.get(7));
                        }
                    } else {
                        System.out.println("You missed fish.");
                        inventoryItems.remove(products.get(7));
                    }
                    missChance = (int) (Math.random() * 100);
                } else
                    System.out.println("You dont have any " + products.get(7).getName() + "!");
                break;
            case "Sea":
                if (inventoryItems.contains(products.get(8))) {
                    catchChance = 40;
                    for (int i = 0; i < inventoryItems.size(); i++) {
                        if (inventoryItems.get(i) == products.get(6) || inventoryItems.get(i) == products.get(5) || inventoryItems.get(i) == products.get(4) || inventoryItems.get(i) == products.get(3))
                            catchChance = 100;
                        else if (inventoryItems.get(i) == products.get(2) && catchChance != 100)
                            catchChance = 85;
                        else if (inventoryItems.get(i) == products.get(1) && catchChance != 100)
                            catchChance = 70;
                        else if (inventoryItems.get(i) == products.get(0) && catchChance != 100)
                            catchChance = 55;
                    }
                    if (missChance <= catchChance) {
                        if (caughtFishes.containsKey(type.getType())) {
                            if (type.isAgressive() && missRate < 30) {
                                System.out.println("You got attacked by " + type.getType() + "! (Lost 250$)");
                                if (myInventory.wallet >= 250)
                                    myInventory.wallet -= 250;
                                else
                                    myInventory.wallet = 0;
                            } else {
                                caughtFishes.replace(type.getType(), caughtFishes.get(type.getType()), caughtFishes.get(type.getType()) + 1);
                                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "You got a/an " + type.getType() + "!" + ConsoleColors.RESET);
                            }
                            inventoryItems.remove(products.get(8));
                        } else {
                            if (type.isAgressive() && (!caughtFishes.toString().contains("Shark") || !caughtFishes.toString().contains("Piranha"))) {
                                achievementsUnlocked.add("Was Too Close");
                                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Achievement Unlocked: Was Too Close!" + ConsoleColors.RESET);
                            }
                            if (type.isAgressive() && missRate < 30) {
                                System.out.println("You got attacked by " + type.getType() + "! (Lost 250$)");
                                if (myInventory.wallet >= 250) {
                                    myInventory.wallet -= 250;
                                }
                                else {
                                    myInventory.wallet = 0;
                                }
                            } else {
                                caughtFishes.put(type.getType(), 1);
                                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "You got a/an " + type.getType() + "!" + ConsoleColors.RESET);
                                inventoryItems.remove(products.get(8));
                            }
                        }
                    } else {
                        System.out.println("You missed fish.");
                        inventoryItems.remove(products.get(8));
                    }
                    missChance = (int) (Math.random() * 100);
                } else
                    System.out.println("You dont have any " + products.get(8).getName() + "!");
                break;
            case "Ocean":
                if (inventoryItems.contains(products.get(9))) {
                    catchChance = 10;
                    for (int i = 0; i < inventoryItems.size(); i++) {
                        if (inventoryItems.get(i) == products.get(6) || inventoryItems.get(i) == products.get(5))
                            catchChance = 100;
                        else if (inventoryItems.get(i) == products.get(4) && catchChance != 100)
                            catchChance = 85;
                        else if (inventoryItems.get(i) == products.get(3) && catchChance != 100)
                            catchChance = 70;
                        else if (inventoryItems.get(i) == products.get(2) && catchChance != 100)
                            catchChance = 55;
                        else if (inventoryItems.get(i) == products.get(1) && catchChance != 100)
                            catchChance = 40;
                        else if (inventoryItems.get(i) == products.get(0) && catchChance != 100)
                            catchChance = 25;
                    }
                    if (Math.random() * 100 <= catchChance) {
                        if (caughtFishes.containsKey(type.getType())) {
                            if (type.isAgressive() && missRate < 50) {
                                System.out.println("You got attacked by " + type.getType() + "! (Lost 400$)");
                                if (myInventory.wallet >= 400) {
                                    myInventory.wallet -= 400;
                                }
                                else {
                                    myInventory.wallet = 0;
                                }
                            } else {
                                caughtFishes.replace(type.getType(), caughtFishes.get(type.getType()), caughtFishes.get(type.getType()) + 1);
                                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "You got a/an " + type.getType() + "!" + ConsoleColors.RESET);
                            }
                            inventoryItems.remove(products.get(9));
                        } else {
                            if (type.isAgressive() && (!caughtFishes.toString().contains("Shark") || !caughtFishes.toString().contains("Piranha"))) {
                                achievementsUnlocked.add("Was Too Close");
                                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Achievement Unlocked: Was Too Close!" + ConsoleColors.RESET);
                            }
                            if (type.isAgressive() && missRate < 100) {
                                System.out.println("You got attacked by " + type.getType() + "! (Lost 400$)");
                                if (myInventory.wallet >= 400) {
                                    myInventory.wallet -= 400;
                                }
                                else {
                                    myInventory.wallet = 0;
                                }
                            } else {
                                caughtFishes.put(type.getType(), 1);
                                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "You got a/an " + type.getType() + "!" + ConsoleColors.RESET);
                                inventoryItems.remove(products.get(9));
                            }
                        }
                    } else {
                        System.out.println("You missed fish.");
                        inventoryItems.remove(products.get(9));
                    }
                } else
                    System.out.println("You dont have any " + products.get(9).getName() + "!");
                break;
        }
    }

    private void sellFish() {
        double parrotBonus = 1;
        if (inventoryItems.contains(products.get(15)))
            parrotBonus += 0.25;
        if (caughtFishes.get("Anchovy") != null) {
            myInventory.wallet += caughtFishes.get("Anchovy") * 5 * parrotBonus;
        }
        if (caughtFishes.get("Gold Fish") != null) {
            myInventory.wallet += caughtFishes.get("Gold Fish") * 15 * parrotBonus;
        }
        if (caughtFishes.get("Salmon") != null) {
            myInventory.wallet += caughtFishes.get("Salmon") * 10 * parrotBonus;
        }
        if (caughtFishes.get("Squid") != null) {
            myInventory.wallet += caughtFishes.get("Squid") * 40 * parrotBonus;
        }
        if (caughtFishes.get("Tuna") != null) {
            myInventory.wallet += caughtFishes.get("Tuna") * 30 * parrotBonus;
        }
        if (caughtFishes.get("Piranha") != null) {
            myInventory.wallet += caughtFishes.get("Piranha") * 150 * parrotBonus;
        }
        if (caughtFishes.get("Bass") != null) {
            myInventory.wallet += caughtFishes.get("Bass") * 100 * parrotBonus;

        }
        if (caughtFishes.get("Shark") != null) {
            myInventory.wallet += caughtFishes.get("Shark") * 500 * parrotBonus;
        }
        clearFishStock();
        System.out.println("Current balance: " + myInventory.wallet + "$");
}
    private void goMarket()  {
        int counter=1;
        System.out.println(ConsoleColors.RED_BRIGHT+"********** MARKET **********");
        System.out.println("1) Rods");
        System.out.println("2) Fish Foods");
        System.out.println("3) Boats");
        System.out.println("4) Pets");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"Press 'M' to return main menu."+ConsoleColors.RED_BRIGHT);
        marketCategoryInput=input.next();
            switch (marketCategoryInput) {
                case "1":
                    for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).type.equals("Rod") && inventoryItems.contains(products.get(i))) {
                            System.out.println(counter + ") " + products.get(i).name + "\t" + products.get(i).price + "$ ✔");
                        } else if (products.get(i).type.equals("Rod")) {
                            System.out.println(counter + ") " + products.get(i).name + "\t" + products.get(i).price + "$");
                        }
                        counter++;
                    }
                returnerCheck=input.next();
                switch (returnerCheck) {
                    case "1":
                        buyItem(products.get(0));
                        break;
                    case "2":
                        buyItem(products.get(1));
                        break;
                    case "3":
                        buyItem(products.get(2));
                        break;
                    case "4":
                        buyItem(products.get(3));
                        break;
                    case "5":
                        buyItem(products.get(4));
                        break;
                    case "6":
                        buyItem(products.get(5));
                        break;
                    case "7":
                        buyItem(products.get(6));
                        break;
                }
                break;
            case "2":
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).type.equals("Food") && inventoryItems.contains(products.get(i))) {
                        System.out.println(counter + ") " + products.get(i).name + " x50\t" + products.get(i).getPrice() * 50 + "$ ✔");
                        counter++;
                    }else if (products.get(i).type.equals("Food")) {
                            System.out.println(counter + ") " + products.get(i).name + "x50\t" + products.get(i).getPrice() *50+ "$");
                        counter++;
                        }
                    }
                    returnerCheck=input.next();
                    switch (returnerCheck) {
                        case "1":
                            buyItem(products.get(7));
                            break;
                        case "2":
                            buyItem(products.get(8));
                            break;
                        case "3":
                            buyItem(products.get(9));
                            break;
                    }
                break;
            case "3":
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).type.equals("Boat") && inventoryItems.contains(products.get(i))) {
                        System.out.println(counter + ") " + products.get(i).name + " \t" + products.get(i).price  + "$ ✔");
                        counter++;
                    }
                    else if (products.get(i).type.equals("Boat")) {
                        System.out.println(counter + ") " + products.get(i).name + "\t" + products.get(i).price + "$");
                        counter++;
                    }
                }
                returnerCheck=input.next();
                switch (returnerCheck) {
                    case "1":
                        buyItem(products.get(10));
                        break;
                    case "2":
                        buyItem(products.get(11));
                        break;
                    case "3":
                        buyItem(products.get(12));
                        break;
                }
                break;
            case "4":
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).type.equals("Pet") && inventoryItems.contains(products.get(i))) {
                        System.out.println(counter + ") " + products.get(i).name + " \t" + products.get(i).price  + "$ ✔");
                        counter++;
                    }
                    else if (products.get(i).type.equals("Pet")){
                        System.out.println(counter + ") " + products.get(i).name+"\t"+products.get(i).price+"$");
                        counter++;
                    }
          }
                returnerCheck=input.next();
                switch (returnerCheck) {
                    case "1":
                        buyItem(products.get(13));
                        break;
                    case "2":
                        buyItem(products.get(14));
                        break;
                    case "3":
                        buyItem(products.get(15));
                        break;
                }
                break;
                case "M","m":
                    GUI();
                default:
                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"Type from 1 to 4.");
            }
                    System.out.println("Press 'M' to return main menu.");
                    returnerCheck=input.next();
                    while (true) {
                        if (returnerCheck.toUpperCase(Locale.ROOT).contains("M")){
                            GUI();
                            break;
                        }
                        else
                            returnerCheck= input.next();
                    }
    }
    private void goInventory()  {
        sortInventory();
        listInventory();
    }
    private void sortInventory(){
        ArrayList<MarketProducts> copiedInventory=new ArrayList<>(inventoryItems.size());
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < inventoryItems.size(); j++) {
                 if (inventoryItems.get(j)== products.get(i))
                     copiedInventory.add(inventoryItems.get(j));
            }
        }
        inventoryItems=copiedInventory;
    }
    private void listInventory()  {
        sortInventory();
        ArrayList<String> foodTypes=new ArrayList<>(3);
        int wormAmount=0;
        int butterflyAmount=0;
        int grasshopperAmount=0;
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"----------- Rods -----------");
        for (int i = 0; i < inventoryItems.size(); i++) {
            if (inventoryItems.get(i).getName()=="Worm")
                wormAmount++;
            else if (inventoryItems.get(i).getName()=="Butterfly")
                butterflyAmount++;
            else if (inventoryItems.get(i).getName()=="Grasshopper")
                grasshopperAmount++;
            if(inventoryItems.get(i).type=="Rod")
                System.out.println("-> "+inventoryItems.get(i).getName());
        }
        System.out.println("\n----------- Fish Foods -----------");
        for (int i = 0; i < inventoryItems.size(); i++) {

            if(inventoryItems.get(i).type=="Food"&&!foodTypes.contains(inventoryItems.get(i).name)){
                foodTypes.add(inventoryItems.get(i).getName());
                System.out.print("-> "+inventoryItems.get(i).getName());
                if (inventoryItems.get(i).getName()=="Worm")
                    System.out.println(" x"+wormAmount);
                else if (inventoryItems.get(i).getName()=="Butterfly")
                    System.out.println(" x"+butterflyAmount);
                else if (inventoryItems.get(i).getName()=="Grasshopper")
                    System.out.println(" x"+grasshopperAmount);
            }
        }
        System.out.println("\n----------- Boats -----------");
        for (int i = 0; i < inventoryItems.size(); i++) {
            if(inventoryItems.get(i).type=="Boat")
                System.out.println("-> "+inventoryItems.get(i).getName());
        }
        System.out.println("\n----------- Pets -----------");
        for (int i = 0; i < inventoryItems.size(); i++) {
            if(inventoryItems.get(i).type=="Pet")
                System.out.println("-> "+inventoryItems.get(i).getName());
        }
        System.out.println("\n----------- Caught Fishes -----------");
                System.out.println(caughtFishes);
        System.out.println("Press 'S' to sell fishes.");
        System.out.println(ConsoleColors.RESET+"Press 'M' to return main menu.");
        System.out.println("Current Balance: "+myInventory.wallet+"$");
        returnerCheck=input.next();
        while (true) {
            if (returnerCheck.toUpperCase(Locale.ROOT).contains("S")){
            sellFish();
            break;
        }
            else if (returnerCheck.toUpperCase(Locale.ROOT).contains("M")){
                GUI();
                break;
            }
            else
                returnerCheck= input.next();
        }
    }
    private void goAchievements()  {
        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT+"Achievements Unlocked:");
        for (int i = 0; i < achievementsUnlocked.size(); i++) {
            System.out.println("-> "+achievementsUnlocked.toArray()[i]);
        }
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"Press 'M' to return main menu.");
        returnerCheck=input.next();
        while (true) {
            if (returnerCheck.toUpperCase(Locale.ROOT).contains("M")){
                GUI();
                break;
            }
            else
                returnerCheck= input.next();
        }
    }
    private void goCredits()  {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+"CREDITS\nDeveloper: Arjin Kavalci"+
                "\nAntalya/Turkey\tJuly 2022\nContact:arjin.33@outlook.com\nThanks for playing."+ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"Press 'M' to return main menu.");
        returnerCheck=input.next();
        while (true) {
            if (returnerCheck.toUpperCase(Locale.ROOT).contains("M")){
                GUI();
            break;
            }
            else
                returnerCheck= input.next();
        }
    }
    private void identifyFishes(){
        Fish anchovy=new Fish("Anchovy",5,"Pool",false);
        Fish goldFish=new Fish("Gold Fish",15,"Pool",false);
        Fish salmon=new Fish("Salmon",10,"Lake",false);
        Fish squid=new Fish("Squid",40,"Lake",false);
        Fish tuna=new Fish("Tuna",30,"Sea",false);
        Fish piranha=new Fish("Piranha",150,"Sea",true);
        Fish bass=new Fish("Bass",100,"Ocean",false);
        Fish shark=new Fish("Shark",500,"Ocean",true);
        fishes.add(anchovy);
        fishes.add(goldFish);
        fishes.add(salmon);
        fishes.add(squid);
        fishes.add(tuna);
        fishes.add(piranha);
        fishes.add(bass);
        fishes.add(shark);
    }
    private void clearFishStock() {
        caughtFishes.clear();
        caughtFishes.put("Anchovy",0);
        caughtFishes.put("Gold Fish",0);
        caughtFishes.put("Salmon",0);
        caughtFishes.put("Squid",0);
        caughtFishes.put("Tuna",0);
        caughtFishes.put("Piranha",0);
        caughtFishes.put("Bass",0);
        caughtFishes.put("Shark",0);
    }
    private void identifyAchievements(){
        achievements.add("First Rare Fish");
        achievements.add("Commercial Man");
        achievements.add("New Friends");
        achievements.add("Animal Lover");
        achievements.add("Was Too Close");
    }
    private void identifyMarketProducts(){
        //Rods
        MarketProducts ironRod=new MarketProducts("Rod","Iron Rod",50,"%15 more chance to catch fish.");
        MarketProducts steelRod=new MarketProducts("Rod","Steel Rod",150,"%30 more chance to catch fish.");
        MarketProducts crimsteelRod=new MarketProducts("Rod","Crimsteel Rod",250,"%45 more chance to catch fish.");
        MarketProducts silverRod=new MarketProducts("Rod","Silver Rod",500,"%60 more chance to catch fish.");
        MarketProducts goldenRod=new MarketProducts("Rod","Golden Rod",2000,"%75 more chance to catch fish.");
        MarketProducts platiniumRod=new MarketProducts("Rod","Platinium Rod",4000,"%90 more chance to catch fish.");
        MarketProducts ancientRod=new MarketProducts("Rod","Ancient Rod",10000,"Mystic rod for fishing lvl 50+.");
        //Fish Foods
        MarketProducts worm=new MarketProducts("Food","Worm",5,"You need this for fishing in the lake.");
        MarketProducts butterfly=new MarketProducts("Food","Butterfly",10,"You need this for fishing in the sea.");
        MarketProducts grasshopper=new MarketProducts("Food","Grasshopper",25,"You need this for fishing in the ocean.");
        //Boats
        MarketProducts sailboat=new MarketProducts("Boat","Sailboat",1000,"Necessary for fishing at sea.");
        MarketProducts ship=new MarketProducts("Boat","Ship",5000,"Necessary for fishing at ocean.");
        MarketProducts yacht=new MarketProducts("Boat","Yacht",30000,"Just shows that you are rich.");
        //Pets
        MarketProducts dog=new MarketProducts("Pet","Dog",2000,"Protects frpm attacks by %25 rate.");
        MarketProducts cat=new MarketProducts("Pet","Cat",5000,"Discounts while buying fish foods.");
        MarketProducts parrot=new MarketProducts("Pet","Parrot",10000,"%25 more gold.");
        products.add(ironRod);
        products.add(steelRod);
        products.add(crimsteelRod);
        products.add(silverRod);
        products.add(goldenRod);
        products.add(platiniumRod);
        products.add(ancientRod);
        products.add(worm);
        products.add(butterfly);
        products.add(grasshopper);
        products.add(sailboat);
        products.add(ship);
        products.add(yacht);
        products.add(dog);
        products.add(cat);
        products.add(parrot);
    }
}
class Fish {
    private String type;
    private int marketPrice;
    private String habitat;
    private boolean isAgressive;
    public Fish(String type,int marketPrice,String habitat,boolean isAgressive){
        this.type=type;
        this.marketPrice=marketPrice;
        this.habitat=habitat;
        this.isAgressive=isAgressive;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getMarketPrice() {
        return marketPrice;
    }
    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }
    public String getHabitat() {
        return habitat;
    }
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    public boolean isAgressive() {
        return isAgressive;
    }
    public void setAgressive(boolean agressive) {
        isAgressive = agressive;
    }
}
class MarketProducts  {
    String type;
    String name;
    int price;
    String explanation;
    public MarketProducts(String type,String name,int price,String explanation){
        this.type=type;
        this.name=name;
        this.price=price;
        this.explanation=explanation;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
class Inventory {
    int wallet;
    ArrayList<MarketProducts> invenoryItems=new ArrayList<>();
    public Inventory(int wallet){
        this.wallet=wallet;
    }

}
