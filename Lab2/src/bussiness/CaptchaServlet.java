package bussiness;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CaptchaServlet
 */
@WebServlet("/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Random random = new Random();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaptchaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String text = getRandomText();
		response.setContentType("image/jpeg");
		BufferedImage awtImage = new BufferedImage(250, 150, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = awtImage.getGraphics();

		Graphics2D graphic2D = (Graphics2D) graphics;
		graphic2D.setColor(Color.WHITE);
		graphic2D.fillRect(0, 0, 250, 150);

		for (int i = 0; i < text.length(); i++) {
			drawLetterRandomly(graphic2D, 5 + i * 240 / text.length(), text.charAt(i));
		}

		for (int i = 0; i < 50; i++) {
			drawTransparentSquareRandomly(graphic2D);
		}

		try {
			graphic2D = awtImage.createGraphics();
			OutputStream out = response.getOutputStream();
			ImageIO.write(awtImage, "png", out);
			out.close();
			request.getSession().setAttribute("CaptchaBean", text);
		} catch (IOException log) {
			System.out.println(log);
		}
	}

	private void drawLetterRandomly(Graphics2D graphics, int x, char letter) {

		graphics.setColor(getRandomColor());
		graphics.setFont(new Font(getRandomFontName(), random.nextInt(5), 25 + random.nextInt(40)));

		graphics.drawString(Character.toString(letter), x, 100);
	}

	private void drawTransparentSquareRandomly(Graphics2D graphics) {

		graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(50)));
		graphics.fillRect(random.nextInt(250), random.nextInt(250), 50 + random.nextInt(100), 50 + random.nextInt(100));
	}

	private String getRandomText() {
		StringBuilder buffer = new StringBuilder(7);
		for (int i = 0; i < 7; i++) {
			if (random.nextBoolean()) {
				// number
				int charNo = 48 + random.nextInt(57 - 48);
				buffer.append((char) charNo);
			} else {
				// letter
				if (random.nextBoolean()) {
					// small
					int charNo = 97 + random.nextInt(122 - 97);
					buffer.append((char) charNo);
				} else {
					// big
					int charNo = 65 + random.nextInt(90 - 65);
					buffer.append((char) charNo);
				}
			}
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	private Color getRandomColor() {
		return new Color(random.nextInt(200), random.nextInt(200), random.nextInt(200));
	}

	private String getRandomFontName() {
		// String[] fonts =
		// GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		String[] fonts = { "Times new roman", "Tahoma", "Verdana", "Serif" };
		return fonts[random.nextInt(fonts.length)];
	}
}
