package com.ecommerce.config;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.entity.Cart;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, 
                                    ProductRepository productRepository, 
                                    CartRepository cartRepository,
                                    PasswordEncoder passwordEncoder) {
        return args -> {
            // Seed Admin User
            if (!userRepository.existsByEmail("admin@ecommerce.com")) {
                User admin = User.builder()
                        .username("Admin")
                        .email("admin@ecommerce.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role(User.Role.ROLE_ADMIN)
                        .build();
                admin = userRepository.save(admin);
                
                // Seed Admin Cart
                if (cartRepository.findByUserId(admin.getId()).isEmpty()) {
                    Cart adminCart = Cart.builder().user(admin).build();
                    cartRepository.save(adminCart);
                }
                
                System.out.println("Admin user seeded: admin@ecommerce.com / admin123");
                System.out.println("Admin cart seeded");
            }

            // Seed Sample Products
            if (productRepository.count() == 0) {
                List<Product> products = List.of(
                    Product.builder()
                        .name("Neon Pro Smartphone")
                        .description("High-performance smartphone with a futuristic design.")
                        .price(new BigDecimal("59999.00"))
                        .discountPrice(new BigDecimal("54999.00"))
                        .stockQuantity(50)
                        .category("Electronics")
                        .imageUrl("https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?q=80&w=400&auto=format&fit=crop")
                        .rating(4.8)
                        .reviewsCount(128)
                        .isFeatured(true)
                        .build(),
                    Product.builder()
                        .name("Cyber Gaming Laptop")
                        .description("Built for speed and power. Ultra-fast refresh rate.")
                        .price(new BigDecimal("124999.00"))
                        .discountPrice(new BigDecimal("119999.00"))
                        .stockQuantity(20)
                        .category("Electronics")
                        .imageUrl("https://images.unsplash.com/photo-1603302576837-37561b2e2302?q=80&w=400&auto=format&fit=crop")
                        .rating(4.9)
                        .reviewsCount(56)
                        .isFeatured(true)
                        .build(),
                    Product.builder()
                        .name("Stealth Wireless Headphones")
                        .description("Crystal clear sound with active noise cancellation.")
                        .price(new BigDecimal("14999.00"))
                        .stockQuantity(100)
                        .category("Electronics")
                        .imageUrl("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=400&auto=format&fit=crop")
                        .rating(4.7)
                        .reviewsCount(210)
                        .build(),
                    Product.builder()
                        .name("Minimalist Smart Watch")
                        .description("Track your health and stay connected in style.")
                        .price(new BigDecimal("8999.00"))
                        .discountPrice(new BigDecimal("7999.00"))
                        .stockQuantity(75)
                        .category("Electronics")
                        .imageUrl("https://images.unsplash.com/photo-1523275335684-37898b6baf30?q=80&w=400&auto=format&fit=crop")
                        .rating(4.5)
                        .reviewsCount(89)
                        .build(),
                    Product.builder()
                        .name("Streetwear Hoodie")
                        .description("Premium cotton hoodie with reflective logos.")
                        .price(new BigDecimal("2999.00"))
                        .stockQuantity(200)
                        .category("Clothing")
                        .imageUrl("https://images.unsplash.com/photo-1556821840-3a63f95609a7?q=80&w=400&auto=format&fit=crop")
                        .rating(4.4)
                        .reviewsCount(145)
                        .build(),
                    Product.builder()
                        .name("Classic Denim Jacket")
                        .description("Timeless blue denim jacket with a modern fit.")
                        .price(new BigDecimal("4499.00"))
                        .stockQuantity(150)
                        .category("Clothing")
                        .imageUrl("https://images.unsplash.com/photo-1542272604-787c3835535d?q=80&w=400&auto=format&fit=crop")
                        .rating(4.6)
                        .reviewsCount(72)
                        .build(),
                    Product.builder()
                        .name("The Pragmatic Programmer")
                        .description("The classic guide for developers to improve their craft.")
                        .price(new BigDecimal("1499.00"))
                        .stockQuantity(300)
                        .category("Books")
                        .imageUrl("https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?q=80&w=400&auto=format&fit=crop")
                        .rating(4.9)
                        .reviewsCount(1200)
                        .isFeatured(true)
                        .build(),
                    Product.builder()
                        .name("Clean Code")
                        .description("A handbook of agile software craftsmanship.")
                        .price(new BigDecimal("1299.00"))
                        .stockQuantity(250)
                        .category("Books")
                        .imageUrl("https://images.unsplash.com/photo-1589998059171-988d887df646?q=80&w=400&auto=format&fit=crop")
                        .rating(4.8)
                        .reviewsCount(850)
                        .build(),
                    Product.builder()
                        .name("Ergonomic Office Chair")
                        .description("Experience ultimate comfort during long work hours.")
                        .price(new BigDecimal("18999.00"))
                        .stockQuantity(40)
                        .category("Home")
                        .imageUrl("https://images.unsplash.com/photo-1505797149-43b0ad76620e?q=80&w=400&auto=format&fit=crop")
                        .rating(4.7)
                        .reviewsCount(45)
                        .build(),
                    Product.builder()
                        .name("Professional Yoga Mat")
                        .description("Extra thick, non-slip mat for your daily practice.")
                        .price(new BigDecimal("2499.00"))
                        .stockQuantity(120)
                        .category("Sports")
                        .imageUrl("https://images.unsplash.com/photo-1518611012118-2969c6360ecb?q=80&w=400&auto=format&fit=crop")
                        .rating(4.8)
                        .reviewsCount(160)
                        .build(),
                    Product.builder()
                        .name("Hydrating Face Serum")
                        .description("Revitalize your skin with our premium organic serum.")
                        .price(new BigDecimal("1899.00"))
                        .stockQuantity(85)
                        .category("Beauty")
                        .imageUrl("https://images.unsplash.com/photo-1620916566398-39f1143af7be?q=80&w=400&auto=format&fit=crop")
                        .rating(4.5)
                        .reviewsCount(94)
                        .build(),
                    Product.builder()
                        .name("Wireless Mechanical Keyboard")
                        .description("RGB backlit with tactile blue switches.")
                        .price(new BigDecimal("7499.00"))
                        .stockQuantity(60)
                        .category("Electronics")
                        .imageUrl("https://images.unsplash.com/photo-1511467687858-23d96c32e4ae?q=80&w=400&auto=format&fit=crop")
                        .rating(4.7)
                        .reviewsCount(112)
                        .build(),
                    Product.builder()
                        .name("Vintage Film Camera")
                        .description("Classic 35mm film camera for that retro aesthetic.")
                        .price(new BigDecimal("12999.00"))
                        .stockQuantity(15)
                        .category("Electronics")
                        .imageUrl("https://images.unsplash.com/photo-1516035069371-29a1b244cc32?q=80&w=400&auto=format&fit=crop")
                        .rating(4.6)
                        .reviewsCount(28)
                        .isFeatured(true)
                        .build(),
                    Product.builder()
                        .name("Canvas Messenger Bag")
                        .description("Durable water-resistant bag for city commutes.")
                        .price(new BigDecimal("3499.00"))
                        .stockQuantity(100)
                        .category("Clothing")
                        .imageUrl("https://images.unsplash.com/photo-1548036328-c9fa89d128fa?q=80&w=400&auto=format&fit=crop")
                        .rating(4.4)
                        .reviewsCount(67)
                        .build(),
                    Product.builder()
                        .name("Wool Overcoat")
                        .description("Keep warm in style with this premium wool blend coat.")
                        .price(new BigDecimal("8999.00"))
                        .stockQuantity(50)
                        .category("Clothing")
                        .imageUrl("https://images.unsplash.com/photo-1539533397308-a61e761708ac?q=80&w=400&auto=format&fit=crop")
                        .rating(4.7)
                        .reviewsCount(34)
                        .build(),
                    Product.builder()
                        .name("Clean Architecture")
                        .description("A craftsman's guide to software structure and design.")
                        .price(new BigDecimal("1599.00"))
                        .stockQuantity(200)
                        .category("Books")
                        .imageUrl("https://images.unsplash.com/photo-1516979187457-637abb4f9353?q=80&w=400&auto=format&fit=crop")
                        .rating(4.9)
                        .reviewsCount(540)
                        .build(),
                    Product.builder()
                        .name("Modern Art Canvas")
                        .description("Hand-painted abstract art on large canvas.")
                        .price(new BigDecimal("5499.00"))
                        .stockQuantity(10)
                        .category("Home")
                        .imageUrl("https://images.unsplash.com/photo-1460661419201-fd4cecea8f82?q=80&w=400&auto=format&fit=crop")
                        .rating(4.5)
                        .reviewsCount(12)
                        .build(),
                    Product.builder()
                        .name("Smart Coffee Mug")
                        .description("Keep your coffee at precisely the temperature you like.")
                        .price(new BigDecimal("4999.00"))
                        .stockQuantity(80)
                        .category("Home")
                        .imageUrl("https://images.unsplash.com/photo-1517142089942-ba376ce32a2e?q=80&w=400&auto=format&fit=crop")
                        .rating(4.3)
                        .reviewsCount(58)
                        .build(),
                    Product.builder()
                        .name("Carbon Fiber Road Bike")
                        .description("Ultra-lightweight frame for professional performance.")
                        .price(new BigDecimal("185000.00"))
                        .stockQuantity(5)
                        .category("Sports")
                        .imageUrl("https://images.unsplash.com/photo-1485965120184-e220f721d03e?q=80&w=400&auto=format&fit=crop")
                        .rating(5.0)
                        .reviewsCount(8)
                        .isFeatured(true)
                        .build(),
                    Product.builder()
                        .name("Luxury Perfume Set")
                        .description("Exquisite fragrances for all occasions.")
                        .price(new BigDecimal("12499.00"))
                        .stockQuantity(30)
                        .category("Beauty")
                        .imageUrl("https://images.unsplash.com/photo-1541643600914-78b084683601?q=80&w=400&auto=format&fit=crop")
                        .rating(4.8)
                        .reviewsCount(42)
                        .build(),
                    Product.builder()
                        .name("Noise Cancelling Earbuds")
                        .description("Compact, lightweight, and powerful sound.")
                        .price(new BigDecimal("12999.00"))
                        .stockQuantity(150)
                        .category("Electronics")
                        .imageUrl("https://images.unsplash.com/photo-1590658268037-6bf12165a8df?q=80&w=400&auto=format&fit=crop")
                        .rating(4.7)
                        .reviewsCount(175)
                        .build(),
                    Product.builder()
                        .name("Leather Journal")
                        .description("Authentic leather-bound journal for your creative thoughts.")
                        .price(new BigDecimal("1899.00"))
                        .stockQuantity(200)
                        .category("Home")
                        .imageUrl("https://images.unsplash.com/photo-1512820790803-83ca734da794?q=80&w=400&auto=format&fit=crop")
                        .rating(4.6)
                        .reviewsCount(124)
                        .build()
                );
                productRepository.saveAll(products);
                System.out.println("Comprehensive sample products seeded (22 total)!");
            }
        };
    }
}
