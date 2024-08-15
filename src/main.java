import java.util.Scanner;

class Player {
    private String name;
    private Double attackPower;
    private Double health;
    private Weapon weapon;
    private Armor armor;
    private Race race;

    public Player(String name, Race race, Weapon weapon, Armor armor) {
        this.name = name;
        this.race = race;
        this.weapon = weapon;
        this.armor = armor;
        this.health = race.getHealthBonus();
        this.attackPower = race.getAttackBonus() + weapon.getAttackPower();
    }

    public void display() {
        System.out.println("Profil Pemain:");
        System.out.println("Nama: " + this.name);
        System.out.println("Ras: " + this.race.getName());
        System.out.println("Kesehatan: " + this.health);
        System.out.println("Kekuatan Serangan: " + this.attackPower);
        System.out.println("Senjata: " + this.weapon.getName() + " (Kekuatan Serangan: " + this.weapon.getAttackPower() + ")");
        System.out.println("Armor: " + this.armor.getName() + " (Kekuatan Pertahanan: " + this.armor.getDefencePower() + ")");
    }

    public void attack(Enemy enemy) {
        System.out.println(this.name + " menyerang " + enemy.getName() + " dengan " + this.weapon.getName() + "!");
        enemy.takeDamage(this.attackPower);
    }

    public void takeDamage(Double damage) {
        this.health -= damage;
        System.out.println(this.name + " memiliki " + this.health + " kesehatan tersisa.");
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

}

class Weapon {
    private String name;
    private Double attackPower;

    public Weapon(String name, Double attackPower) {
        this.name = name;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public Double getAttackPower() {
        return attackPower;
    }

    public void display() {
        System.out.println("Nama Senjata: " + this.name);
        System.out.println("Kekuatan Serangan Senjata: " + this.attackPower);
    }
}

class Armor {
    private String name;
    private Double defencePower;

    public Armor(String name, Double defencePower) {
        this.name = name;
        this.defencePower = defencePower;
    }

    public String getName() {
        return name;
    }

    public Double getDefencePower() {
        return defencePower;
    }

    public void display() {
        System.out.println("Nama Armor: " + this.name);
        System.out.println("Kekuatan Pertahanan Armor: " + this.defencePower);
    }
}

class Race {
    private String name;
    private Double attackBonus;
    private Double healthBonus;

    public Race(String name, Double attackBonus, Double healthBonus) {
        this.name = name;
        this.attackBonus = attackBonus;
        this.healthBonus = healthBonus;
    }

    public String getName() {
        return name;
    }

    public Double getAttackBonus() {
        return attackBonus;
    }

    public Double getHealthBonus() {
        return healthBonus;
    }
}

class Enemy {
    private String name;
    private Double health;
    private Double attackPower;

    public Enemy(String name, Double health, Double attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public void takeDamage(Double damage) {
        this.health -= damage;
        System.out.println(this.name + " memiliki " + this.health + " kesehatan tersisa.");
    }

    public void attack(Player player) {
        System.out.println(this.name + " menyerang " + player.getName() + "!");
        player.takeDamage(this.attackPower);
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membuat objek Ras
        Race human = new Race("Manusia", 10.0, 100.0);
        Race elf = new Race("Elf", 15.0, 80.0);
        Race orc = new Race("Orc", 20.0, 120.0);
        Race[] races = { human, elf, orc };

        // Membuat objek Senjata
        Weapon sword = new Weapon("Pedang", 30.0);
        Weapon axe = new Weapon("Kapak", 40.0);
        Weapon bow = new Weapon("Busur", 25.0);
        Weapon[] weapons = { sword, axe, bow };

        // Membuat objek Armor
        Armor shield = new Armor("Perisai", 20.0);
        Armor helmet = new Armor("Helm", 15.0);
        Armor armorSuit = new Armor("Baju Zirah", 25.0);
        Armor[] armors = { shield, helmet, armorSuit };

        // Memilih Ras
        System.out.println("Pilih ras Anda:");
        for (int i = 0; i < races.length; i++) {
            System.out.println((i + 1) + ". " + races[i].getName() + " (Bonus Serangan: " + races[i].getAttackBonus() + ", Bonus Kesehatan: " + races[i].getHealthBonus() + ")");
        }
        System.out.print("Masukkan nomor pilihan Anda: ");
        int raceChoice = scanner.nextInt();
        Race chosenRace = races[raceChoice - 1];

        // Memilih Senjata
        System.out.println("\nPilih senjata Anda:");
        for (int i = 0; i < weapons.length; i++) {
            System.out.println((i + 1) + ". " + weapons[i].getName() + " (Kekuatan Serangan: " + weapons[i].getAttackPower() + ")");
        }
        System.out.print("Masukkan nomor pilihan Anda: ");
        int weaponChoice = scanner.nextInt();
        Weapon chosenWeapon = weapons[weaponChoice - 1];

        // Memilih Armor
        System.out.println("\nPilih armor Anda:");
        for (int i = 0; i < armors.length; i++) {
            System.out.println((i + 1) + ". " + armors[i].getName() + " (Kekuatan Pertahanan: " + armors[i].getDefencePower() + ")");
        }
        System.out.print("Masukkan nomor pilihan Anda: ");
        int armorChoice = scanner.nextInt();
        Armor chosenArmor = armors[armorChoice - 1];

        // Membuat objek Player
        Player player1 = new Player("Arthur", chosenRace, chosenWeapon, chosenArmor);

        // Membuat musuh
        Enemy enemy1 = new Enemy("Goblin", 50.0, 15.0);

        // Menampilkan menu
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Tampilkan Profil Pemain");
            System.out.println("2. Tampilkan Detail Senjata");
            System.out.println("3. Tampilkan Detail Armor");
            System.out.println("4. Bertarung dengan Musuh");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    player1.display();
                    break;
                case 2:
                    player1.getWeapon().display();
                    break;
                case 3:
                    player1.getArmor().display();
                    break;
                case 4:
                    fight(player1, enemy1);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }

    static void fight(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPertarungan dimulai!");

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\nApa yang ingin Anda lakukan?");
            System.out.println("1. Serang musuh");
            System.out.println("2. Keluar pertarungan");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                player.attack(enemy);
                if (enemy.isAlive()) {
                    enemy.attack(player);
                }
            } else if (choice == 2) {
                System.out.println("Anda melarikan diri dari pertarungan.");
                break;
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        if (!player.isAlive()) {
            System.out.println("Anda kalah dalam pertarungan.");
        } else if (!enemy.isAlive()) {
            System.out.println("Anda menang dalam pertarungan!");
        }
    }
}
