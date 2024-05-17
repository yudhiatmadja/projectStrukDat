import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("masukkan path gambar : ");
        String imagePath = scanner.nextLine();

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            if (image == null) {
                System.out.println("Gambar tidak ditemukan atau format tidak didukung.");
                return;
            }

            
            Map<Integer, Integer> colorFrequencyMap = new HashMap<>();

           
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int pixelColor = image.getRGB(x, y);
                    colorFrequencyMap.put(pixelColor, colorFrequencyMap.getOrDefault(pixelColor, 0) + 1);
                }
            }

           
            int dominantColor = 0;
            int maxFrequency = 0;

            for (Map.Entry<Integer, Integer> entry : colorFrequencyMap.entrySet()) {
                if (entry.getValue() > maxFrequency) {
                    dominantColor = entry.getKey();
                    maxFrequency = entry.getValue();
                }
            }

            
            System.out.println("Warna yang muncul pada gambar:");
            for (Map.Entry<Integer, Integer> entry : colorFrequencyMap.entrySet()) {
                System.out.printf("Warna: #%06X, Frekuensi: %d\n", entry.getKey() & 0xFFFFFF, entry.getValue());
            }

            System.out.printf("Warna yang paling dominan: #%06X, Frekuensi: %d\n", dominantColor & 0xFFFFFF,
                    maxFrequency);

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat gambar: " + e.getMessage());
        }
    }
}
