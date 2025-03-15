import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform; // Dodaj ten import

 class RotatedRectangles extends JFrame {

    // Klasa odpowiedzialna za rysowanie
    class DrawingPanel extends JPanel {
        private BufferedImage backgroundImage;

        public DrawingPanel() {
            try {
                // Wczytanie tła
                backgroundImage = ImageIO.read(new File("C:\\Users\\sebas\\IdeaProjects\\javaproject\\src\\torzysko.png"));
            } catch (IOException e) {
                System.out.println("Błąd wczytywania obrazu: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g; // Konwersja na Graphics2D

            // Rysowanie tła
            if (backgroundImage != null) {
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            // Włączenie wygładzania (poprawia jakość grafiki)
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Obrót prostokąta 1
            g2d.setColor(Color.RED);
            AffineTransform originalTransform = g2d.getTransform(); // Zapisujemy oryginalną transformację
            g2d.rotate(Math.toRadians(-33), 1125, 290); // Kąt w stopniach + punkt obrotu
            g2d.fillRect(1120, 293,10, 5);
            g2d.setTransform(originalTransform); // Przywracamy oryginalną transformację
            // Reset transformacji, aby uniknąć dalszych obrotów


            // Obrót prostokąta 2
            g2d.setColor(Color.BLUE);
            g2d.rotate(Math.toRadians(-33), 1125, 300); // Obrót w przeciwnym kierunku
            g2d.fillRect(1125, 301, 10, 5);
            g2d.setTransform(originalTransform); // Przywracamy oryginalną transformację
        }
    }

    public RotatedRectangles() {
        setTitle("Obrócone Prostokąty na Tle");
        setSize(1300, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new DrawingPanel()); // Dodanie panelu rysującego

        setVisible(true);
    }

    public static void main(String[] args) {
        new RotatedRectangles();
    }
}
