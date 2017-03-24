package inf101.v17.boulderdash.bdobjects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.io.InputStream;

import inf101.v17.boulderdash.maps.BDMap;

/**
 * An implementation of sand which simply disappears when the player walks over
 * it. Nothing to do here.
 *
 * @author larsjaffke
 *
 */
public class BDSand extends AbstractBDObject {

	private ImagePattern image;
	
	public BDSand(BDMap owner) {
		super(owner);
		InputStream resourceAsStream = getClass().getResourceAsStream("Sand.png");
		ImagePattern image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1.0,1.0, true);
		this.image=image;
	}

	@Override
	public Paint getColor() {
		return image;
	}

	@Override
	public void step() {
		// DO NOTHING
	}
}
