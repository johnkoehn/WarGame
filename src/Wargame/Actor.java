package Wargame;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

public class Actor {
	private Texture texture;
	private Sprite actor;
	private Point p;

	public Actor(float fxPos, float fyPos, String textureFile)
			throws IOException {
		p = new Point(fxPos, fyPos);

		texture = new Texture();

		// load the texture
		texture.loadFromFile(Paths.get(textureFile));

		// set texture to smooth
		texture.setSmooth(true);

		// now create the sprite
		actor = new Sprite(texture);
		actor.setPosition(p.getX(), p.getY());
	}

	public Actor(Point p, String textureFile) throws IOException {
		texture = new Texture();

		// load the texture
		texture.loadFromFile(Paths.get(textureFile));

		// set texture to smooth
		texture.setSmooth(true);

		// now create the sprite
		actor = new Sprite(texture);
		actor.setPosition(p.getX(), p.getY());
	}

	public Point getPoint() {
		return p;
	}

	public float getX() {
		return p.getX();
	}

	public float getY() {
		return p.getY();
	}

	public void updateX(float deltaX) {
		p.changeX(deltaX);
		actor.move(deltaX, 0);
	}

	public void updateY(float deltaY) {
		p.changeY(deltaY);
		actor.move(0, deltaY);
	}

	public void updatePoint(Point newP) {
		p = newP;
		// actor.move(, arg1);
	}

	public Sprite getActor() {
		return actor;
	}

	public int getWidth() {
		Vector2i size = texture.getSize();
		return size.x;
	}

	public int getHeight() {
		Vector2i size = texture.getSize();
		return size.y;
	}
}
