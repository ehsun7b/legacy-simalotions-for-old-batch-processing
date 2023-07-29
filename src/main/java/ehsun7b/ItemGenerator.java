package ehsun7b;

import java.util.Random;

public class ItemGenerator {
    private Random random;

    private String[] itemNames = {
            "Widget", "Gadget", "Device", "Tool", "Appliance", "Accessory", "Equipment", "Apparatus", "Contraption",
            "Widgetizer", "Invention", "TechGear", "Machine", "Gizmo", "Automaton", "Robot", "Contrivance", "Module",
            "Apparat", "Instrument", "Contrivance", "Device", "Contraption", "Appliance", "Gadget", "Machine", "Tool",
            "Mechanism", "Widget", "Automaton", "Gizmo", "Apparatus", "Equipment", "Invention", "TechGear", "Module",
            "Apparat", "Robot", "Instrument", "Invention", "Machine", "Gizmo", "TechGear", "Automaton", "Contrivance",
            "Gadget", "Widget", "Apparatus", "Appliance", "Module", "Tool", "Contraption"
    };

    private String[] descriptions = {
            "High-quality product", "Brand new item", "Innovative design", "Durable and reliable", "Popular choice",
            "Versatile use", "Eco-friendly", "User-friendly", "Advanced technology", "Premium build", "Sleek design",
            "Efficient performance", "Compact size", "Long-lasting", "Stylish appearance", "Affordable price",
            "Easy setup", "Elegant finish", "Top-notch materials", "Precision engineering", "Exceptional value",
            "Modern functionality", "Excellent craftsmanship", "Robust construction", "Smooth operation",
            "Seamless integration", "Intuitive controls", "Enhanced durability", "Impressive features",
            "Superior performance", "Versatile compatibility", "Ergonomic design", "Streamlined functionality",
            "Simple maintenance", "Enhanced security", "Optimal efficiency", "Reliable performance",
            "State-of-the-art technology", "Premium performance", "Sturdy construction", "Elegant design",
            "Intelligent automation", "Outstanding quality", "Effortless operation", "Sleek and modern",
            "Powerful functionality", "Sustainable materials", "Efficient energy consumption", "Advanced safety features",
            "Luxurious appearance"
    };

    public ItemGenerator(Random random) {
        this.random = random;
    }

    public String generateItemName() {
        return itemNames[random.nextInt(itemNames.length)];
    }

    public String generateDescription() {
        return descriptions[random.nextInt(descriptions.length)];
    }

    public int generateStock() {
        return random.nextInt(100) + 1;
    }
}
