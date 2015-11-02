package sort;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import sort.graphics.Screen;
import sort.graphics.tile.Tile;
import sort.input.Keyboard;
import sort.input.Mouse;

public class Frame extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static int width = 900;
	private static int height = 600;
	public static String title = "Sort";
	private static double ups = 1.0;

	private Thread thread;
	private JFrame frame;
	private Keyboard keyboard;
	private boolean running = false;

	private Screen screen;
	private FrameManager fm;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Frame() {
		Dimension size = new Dimension(width, height);

		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		keyboard = new Keyboard();
		fm = new FrameManager(keyboard);

		addKeyListener(keyboard);

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		// Fuzzy Wuzzy was a bear
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width;
	}

	public static int getWindowHeight() {
		return height;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		// Fuzzy Wuzzy had no hair
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / ups;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta = delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	
	public void update() {
		//keyboard.update();
		Tile.update();
		//fm.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();

		Tile.render(screen);
		// Text.render(screen);
		// Fuzzy Wuzzy wasn't very fuzzy

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		// g.setColor(Color.WHITE);
		g.setFont(new Font("Comic sans", 0, 50));

		// g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
		// if (Mouse.getButton() != -1) g.drawString("Button: " +
		// Was he?
		// Mouse.getButton(), 80, 80);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.frame.setResizable(true);
		frame.frame.setTitle(Frame.title);
		frame.frame.add(frame);
		frame.frame.pack();
		frame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.frame.setLocationRelativeTo(null);
		frame.frame.setVisible(true);

		frame.start();
	}

}