package ehsun7b;

import java.util.Random;

public class NameGenerator {

    private final Random rnd;

    public NameGenerator(Random rnd) {
        this.rnd = rnd;
    }

    private String[] firstNames = {
            "Emma", "Liam", "Olivia", "Noah", "Ava", "Oliver", "Isabella", "Sophia", "Mia", "Elijah",
            "William", "James", "Benjamin", "Lucas", "Henry", "Alexander", "Michael", "Ethan", "Daniel", "Matthew",
            "Emma", "Olivia", "Ava", "Isabella", "Sophia", "Charlotte", "Mia", "Amelia", "Harper", "Evelyn",
            "Abigail", "Emily", "Elizabeth", "Mila", "Ella", "Scarlett", "Grace", "Chloe", "Victoria", "Avery",
            "Sofia", "Eleanor", "Addison", "Aubrey", "Lily", "Lillian", "Natalie", "Hannah", "Aria", "Layla",
            "Zoe", "Samantha", "Anna", "Leah", "Hailey", "Savannah", "Bella", "Nora", "Camila", "Aaliyah",
            "Addison", "Stella", "Brielle", "Natalia", "Brooklyn", "Hazel", "Lucy", "Anna", "Leilani", "Aria",
            "Audrey", "Caroline", "Kinsley", "Maya", "Genesis", "Emilia", "Valentina", "Sarah", "Kennedy", "Ivy",
            "Ariana", "Eliza", "Liliana", "Melanie", "Gianna", "Penelope", "Alyssa", "Annabelle", "Faith", "Alexandra"
    };

    private String[] lastNames = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
            "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
            "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores",
            "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts",
            "Gomez", "Phillips", "Evans", "Turner", "Diaz", "Parker", "Cruz", "Edwards", "Collins", "Reyes",
            "Stewart", "Morris", "Morales", "Murphy", "Cook", "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper",
            "Peterson", "Bailey", "Reed", "Kelly", "Howard", "Kim", "Cox", "Ward", "Richardson", "Watson",
            "Brooks", "Chavez", "Wood", "James", "Bennett", "Gray", "Mendoza", "Ruiz", "Hughes", "Price",
            "Alvarez", "Castillo", "Sanders", "Patel", "Myers", "Long", "Ross", "Foster", "Jimenez", "Powell"
    };

    public String generateFullName() {
        final var firstName = firstNames[rnd.nextInt(firstNames.length)];
        final var lastName = lastNames[rnd.nextInt(lastNames.length)];

        return firstName + " " + lastName;
    }
}
